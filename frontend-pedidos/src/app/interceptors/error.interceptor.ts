import { HttpInterceptorFn, HttpErrorResponse } from '@angular/common/http';
import { catchError, throwError } from 'rxjs';
 
export const errorInterceptor: HttpInterceptorFn = (req, next) => {
  return next(req).pipe(
    catchError((error: HttpErrorResponse) => {
      let mensaje = 'Ocurrió un error inesperado';
 
      if (error.status === 0) {
        mensaje = 'No se pudo conectar con el servidor';
      } else if (error.status === 401) {
        mensaje = 'Sesión expirada. Por favor, inicia sesión nuevamente';
      } else if (error.status === 403) {
        mensaje = 'No tienes permiso para realizar esta acción';
      } else if (error.status === 404) {
        mensaje = 'Recurso no encontrado';
      } else if (error.status >= 500) {
        mensaje = 'Error del servidor. Intenta de nuevo más tarde';
      }
 
      console.error(`[HTTP ${error.status}] ${mensaje}`, error);
      // En una app real, aquí mostrarías un toast/snackbar al usuario
      alert(mensaje);
 
      return throwError(() => error);
    })
  );
};
