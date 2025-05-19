import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { ProfessionalCreditService } from '../../../../services/professional-credit.service';
import { ClientService } from '../../../../services/client.service';
import { ProfessionalCredit } from '../../../../models/professional-credit.model';
import { Client } from '../../../../models/client.model';
import { CreditStatus } from '../../../../models/enums/credit-status.enum';

@Component({
  selector: 'app-professional-credit-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './professional-credit-form.component.html',
  styleUrl: './professional-credit-form.component.css'
})
export class ProfessionalCreditFormComponent implements OnInit {
  creditForm!: FormGroup;
  isEditMode = false;
  creditId?: number;
  loading = false;
  error: string | null = null;
  submitted = false;
  clients: Client[] = [];
  loadingClients = false;
  CreditStatus = CreditStatus; // Make enum available in template

  constructor(
    private fb: FormBuilder,
    private professionalCreditService: ProfessionalCreditService,
    private clientService: ClientService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.initForm();
    this.loadClients();

    // Check if we're in edit mode
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id) {
        this.isEditMode = true;
        this.creditId = +id;
        this.loadCredit(this.creditId);
      }
    });
  }

  initForm(): void {
    this.creditForm = this.fb.group({
      amount: ['', [Validators.required, Validators.min(1)]],
      repaymentDuration: ['', [Validators.required, Validators.min(1)]],
      interestRate: ['', [Validators.required, Validators.min(0)]],
      reason: ['', [Validators.required]],
      companyName: ['', [Validators.required]],
      clientId: ['', [Validators.required]],
      status: [CreditStatus.IN_PROGRESS]
    });
  }

  loadClients(): void {
    this.loadingClients = true;
    this.clientService.getAllClients().subscribe({
      next: (clients) => {
        this.clients = clients;
        this.loadingClients = false;
      },
      error: (err) => {
        console.error('Error loading clients', err);
        this.error = 'Failed to load clients. Please try again.';
        this.loadingClients = false;
      }
    });
  }

  loadCredit(id: number): void {
    this.loading = true;
    this.professionalCreditService.getProfessionalCreditById(id).subscribe({
      next: (credit) => {
        this.creditForm.patchValue({
          amount: credit.amount,
          repaymentDuration: credit.repaymentDuration,
          interestRate: credit.interestRate,
          reason: credit.reason,
          companyName: credit.companyName,
          clientId: credit.clientId,
          status: credit.status
        });
        this.loading = false;
      },
      error: (err) => {
        console.error('Error loading professional credit', err);
        this.error = 'Failed to load professional credit details. Please try again.';
        this.loading = false;
      }
    });
  }

  onSubmit(): void {
    this.submitted = true;

    if (this.creditForm.invalid) {
      return;
    }

    this.loading = true;
    const creditData: ProfessionalCredit = {
      ...this.creditForm.value,
      requestDate: new Date()
    };

    if (this.isEditMode && this.creditId) {
      // Update existing credit
      creditData.id = this.creditId;
      this.professionalCreditService.updateProfessionalCredit(this.creditId, creditData).subscribe({
        next: () => {
          this.router.navigate(['/professional-credits']);
        },
        error: (err) => {
          console.error('Error updating professional credit', err);
          this.error = 'Failed to update professional credit. Please try again.';
          this.loading = false;
        }
      });
    } else {
      // Create new credit
      this.professionalCreditService.createProfessionalCredit(creditData).subscribe({
        next: () => {
          this.router.navigate(['/professional-credits']);
        },
        error: (err) => {
          console.error('Error creating professional credit', err);
          this.error = 'Failed to create professional credit. Please try again.';
          this.loading = false;
        }
      });
    }
  }

  // Getter for easy access to form fields
  get f() { return this.creditForm.controls; }
}
