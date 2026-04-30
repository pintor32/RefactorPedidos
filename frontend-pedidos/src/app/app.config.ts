import { ApplicationConfig } from '@angular/core';
import { provideHttpClient } from '@angular/common/http';

/**
 * Configuración inicial de la aplicación.
 *
 * En el Paso 8 del taller vas a agregar el interceptor de errores aquí.
 */
export const appConfig: ApplicationConfig = {
  providers: [
    provideHttpClient()
  ]
};
