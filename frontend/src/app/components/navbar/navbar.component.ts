import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  // Navigation items
  navItems = [
    { path: '/clients', label: 'Clients', icon: 'bi-people' },
    { path: '/personal-credits', label: 'Personal Credits', icon: 'bi-credit-card' },
    { path: '/real-estate-credits', label: 'Real Estate Credits', icon: 'bi-house' },
    { path: '/professional-credits', label: 'Professional Credits', icon: 'bi-briefcase' }
  ];
}
