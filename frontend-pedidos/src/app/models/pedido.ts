import { LineaPedido } from './linea-pedido';
 
export interface Pedido {
  id: number;
  numero: string;
  fechaCreacion: string;
  total: number;
  clienteNombre: string;
  lineas: LineaPedido[];
}
