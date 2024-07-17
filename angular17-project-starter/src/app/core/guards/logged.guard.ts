/**
 * Limitar el accceso a ciertas rutas de la aplicacion
 */

import { CanActivateFn } from "@angular/router";

export const loggedGuard: CanActivateFn = (route, state) => {
    return true;
};