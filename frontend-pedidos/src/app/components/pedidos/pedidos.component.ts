import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Observable, BehaviorSubject, switchMap } from 'rxjs';
import { Pedido } from '../../models/pedido';
import { PedidosService } from '../../services/pedidos.service';
 
@Component({
  selector: 'app-pedidos',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="pedidos-container">
      <h2>Lista de Pedidos</h2>
      <button (click)="recargar()">Recargar</button>
 
      <div *ngIf="pedidos$ | async as pedidos; else cargando">
        <div *ngFor="let p of pedidos" class="pedido-item">
          <span>{{ p.numero }}</span>
          <span>{{ p.total | currency }}</span>
          <button (click)="eliminar(p.id)">Eliminar</button>
        </div>
      </div>
 
      <ng-template #cargando>
        <p>Cargando pedidos...</p>
      </ng-template>
    </div>
  `,
  styles: [`
    .pedidos-container { padding: 20px; }
    .pedido-item {
      display: flex;
      justify-content: space-between;
      padding: 8px;
      border-bottom: 1px solid #eee;
    }
  `]
})
export class PedidosComponent implements OnInit {
 
  private pedidosService = inject(PedidosService);
  private trigger$ = new BehaviorSubject<void>(undefined);
 
  pedidos$!: Observable<Pedido[]>;
 
  ngOnInit(): void {
    this.pedidos$ = this.trigger$.pipe(
      switchMap(() => this.pedidosService.listarTodos())
    );
  }
 
  recargar(): void {
    this.trigger$.next();
  }
 
  eliminar(id: number): void {
    this.pedidosService.eliminar(id).subscribe({
      next: () => this.recargar(),
      error: (err) => console.error('Error al eliminar:', err)
    });
  }
}
