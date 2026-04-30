import { Component } from '@angular/core';
import { PedidosComponent } from './components/pedidos/pedidos.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [PedidosComponent],
  template: `
    <header>
      <h1>Taller Sesión 8 — Sistema de Pedidos</h1>
    </header>
    <main>
      <app-pedidos></app-pedidos>
    </main>
  `,
  styles: [`
    header {
      background-color: #1F4E79;
      color: white;
      padding: 20px;
      text-align: center;
    }
    header h1 {
      margin: 0;
      font-size: 24px;
    }
    main {
      max-width: 900px;
      margin: 20px auto;
      padding: 20px;
      background: white;
      border-radius: 8px;
      box-shadow: 0 2px 4px rgba(0,0,0,0.1);
    }
  `]
})
export class AppComponent {}
