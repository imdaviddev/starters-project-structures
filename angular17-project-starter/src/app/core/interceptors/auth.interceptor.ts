/**
 * Para hacer alguna intercepcion a los datos que entran o que salen, antes de que
 * entren al sistema o antes de que salgan. (validaciones x ejemplo) (agrega un token)
 */

import { HttpInterceptorFn } from "@angular/common/http";

export const authInterceptor: HttpInterceptorFn= (req, next) => {
    return next(req);
}