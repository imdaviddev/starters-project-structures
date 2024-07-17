import { Routes } from '@angular/router';
import { loggedGuard } from './core/guards/logged.guard';

/**
 * Definicion de las rutas de la aplicacion
 */
export const routes: Routes = [
    /** Example
    {
        path: '',
        loadChildren: () => import('./auth/auth.routes').then(m => m.AUTH_ROUTES),
        canActivate: [loggedGuard]
    },
    {
        path: 'articles',
        loadChildren: () => import('./auth/auth.routes').then(m => m.AUTH_ROUTES)
    },
    {
        path: 'dashboard',
        loadChildren: () => import('./auth/auth.routes').then(m => m.AUTH_ROUTES)
    },
    */
];
