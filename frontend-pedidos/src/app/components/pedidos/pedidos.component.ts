import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';

/**
 * Componente Pedidos — Estado INICIAL del taller (con problemas).
 *
 * Problemas que vas a corregir paso a paso:
 *  - Usa any[] en lugar de tipos estrictos (Paso 5)
 *  - Inyecta HttpClient directamente (Paso 6: usar PedidosService)
 *  - URL hardcodeada (Paso 6: mover a environment)
 *  - subscribe sin unsubscribe (Paso 7: async pipe)
 *  - Código duplicado entre ngOnInit y recargar (Paso 7)
 *  - Sin manejo de errores (Paso 8: HttpInterceptor)
 *
 * NO modifiques esta clase hasta que el taller te indique el paso.
 */
@Component({
  selector: 'app-pedidos',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="pedidos-container">
      <h2>Lista de Pedidos</h2>
      <button (click)="recargar()">Recargar</button>

      <div *ngFor="let p of pedidos" class="pedido-item">
        <span class="numero">{{ p.numero }}</span>
        <span class="total">{{ p.total | currency }}</span>
        <button (click)="eliminar(p.id)" class="secondary">Eliminar</button>
      </div>

      <p *ngIf="pedidos.length === 0">No hay pedidos para mostrar.</p>
    </div>
  `,
  styles: [`
    .pedidos-container { padding: 20px; }
    .pedido-item {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 12px;
      border-bottom: 1px solid #eee;
    }
    .numero { font-weight: bold; }
    .total { color: #1F4E79; }
  `]
})
export class PedidosComponent implements OnInit {

  pedidos: any[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.http.get<any[]>('http://localhost:8080/api/pedidos')
      .subscribe(data => {
        this.pedidos = data;
      });
  }

  eliminar(id: number) {
    this.http.delete('http://localhost:8080/api/pedidos/' + id)
      .subscribe(() => {
        this.pedidos = this.pedidos.filter(p => p.id !== id);
      });
  }

  recargar() {
    this.http.get<any[]>('http://localhost:8080/api/pedidos')
      .subscribe(data => {
        this.pedidos = data;
      });
  }
}
