import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { RealEstateCreditService } from '../../../../services/real-estate-credit.service';
import { ClientService } from '../../../../services/client.service';
import { RealEstateCredit } from '../../../../models/real-estate-credit.model';
import { Client } from '../../../../models/client.model';
import { CreditStatus } from '../../../../models/enums/credit-status.enum';
import { PropertyType } from '../../../../models/enums/property-type.enum';

@Component({
  selector: 'app-real-estate-credit-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './real-estate-credit-form.component.html',
  styleUrl: './real-estate-credit-form.component.css'
})
export class RealEstateCreditFormComponent implements OnInit {
  creditForm!: FormGroup;
  isEditMode = false;
  creditId?: number;
  loading = false;
  error: string | null = null;
  submitted = false;
  clients: Client[] = [];
  loadingClients = false;
  CreditStatus = CreditStatus; // Make enum available in template
  PropertyType = PropertyType; // Make enum available in template

  constructor(
    private fb: FormBuilder,
    private realEstateCreditService: RealEstateCreditService,
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
      propertyType: [PropertyType.APARTMENT, [Validators.required]],
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
    this.realEstateCreditService.getRealEstateCreditById(id).subscribe({
      next: (credit) => {
        this.creditForm.patchValue({
          amount: credit.amount,
          repaymentDuration: credit.repaymentDuration,
          interestRate: credit.interestRate,
          propertyType: credit.propertyType,
          clientId: credit.clientId,
          status: credit.status
        });
        this.loading = false;
      },
      error: (err) => {
        console.error('Error loading real estate credit', err);
        this.error = 'Failed to load real estate credit details. Please try again.';
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
    const creditData: RealEstateCredit = {
      ...this.creditForm.value,
      requestDate: new Date()
    };

    if (this.isEditMode && this.creditId) {
      // Update existing credit
      creditData.id = this.creditId;
      this.realEstateCreditService.updateRealEstateCredit(this.creditId, creditData).subscribe({
        next: () => {
          this.router.navigate(['/real-estate-credits']);
        },
        error: (err) => {
          console.error('Error updating real estate credit', err);
          this.error = 'Failed to update real estate credit. Please try again.';
          this.loading = false;
        }
      });
    } else {
      // Create new credit
      this.realEstateCreditService.createRealEstateCredit(creditData).subscribe({
        next: () => {
          this.router.navigate(['/real-estate-credits']);
        },
        error: (err) => {
          console.error('Error creating real estate credit', err);
          this.error = 'Failed to create real estate credit. Please try again.';
          this.loading = false;
        }
      });
    }
  }

  // Getter for easy access to form fields
  get f() { return this.creditForm.controls; }
}
