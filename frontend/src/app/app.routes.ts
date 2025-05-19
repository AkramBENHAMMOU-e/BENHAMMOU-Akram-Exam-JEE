import { Routes } from '@angular/router';
import { ClientListComponent } from './components/clients/client-list.component';
import { PersonalCreditListComponent } from './components/credits/personal/personal-credit-list.component';
import { RealEstateCreditListComponent } from './components/credits/real-estate/real-estate-credit-list.component';
import { ProfessionalCreditListComponent } from './components/credits/professional/professional-credit-list.component';

export const routes: Routes = [
  { path: '', redirectTo: '/clients', pathMatch: 'full' },

  // Client routes
  { path: 'clients', component: ClientListComponent },
  { path: 'clients/new', component: ClientListComponent }, // Replace with ClientFormComponent when created
  { path: 'clients/:id', component: ClientListComponent }, // Replace with ClientDetailComponent when created
  { path: 'clients/:id/edit', component: ClientListComponent }, // Replace with ClientFormComponent when created
  { path: 'clients/:id/credits', component: ClientListComponent }, // Replace with ClientCreditsComponent when created

  // Personal Credit routes
  { path: 'personal-credits', component: PersonalCreditListComponent },
  { path: 'personal-credits/new', component: PersonalCreditListComponent }, // Replace with PersonalCreditFormComponent when created
  { path: 'personal-credits/:id', component: PersonalCreditListComponent }, // Replace with PersonalCreditDetailComponent when created
  { path: 'personal-credits/:id/edit', component: PersonalCreditListComponent }, // Replace with PersonalCreditFormComponent when created

  // Real Estate Credit routes
  { path: 'real-estate-credits', component: RealEstateCreditListComponent },
  { path: 'real-estate-credits/new', component: RealEstateCreditListComponent }, // Replace with RealEstateCreditFormComponent when created
  { path: 'real-estate-credits/:id', component: RealEstateCreditListComponent }, // Replace with RealEstateCreditDetailComponent when created
  { path: 'real-estate-credits/:id/edit', component: RealEstateCreditListComponent }, // Replace with RealEstateCreditFormComponent when created

  // Professional Credit routes
  { path: 'professional-credits', component: ProfessionalCreditListComponent },
  { path: 'professional-credits/new', component: ProfessionalCreditListComponent }, // Replace with ProfessionalCreditFormComponent when created
  { path: 'professional-credits/:id', component: ProfessionalCreditListComponent }, // Replace with ProfessionalCreditDetailComponent when created
  { path: 'professional-credits/:id/edit', component: ProfessionalCreditListComponent }, // Replace with ProfessionalCreditFormComponent when created

  // Fallback route
  { path: '**', redirectTo: '/clients' }
];
