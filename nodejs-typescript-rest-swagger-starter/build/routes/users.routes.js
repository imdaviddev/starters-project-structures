"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = require("express");
const users_controllers_1 = require("../controllers/users.controllers");
const users_validations_1 = require("../utils/validations/users.validations");
// New Router instance
const router = (0, express_1.Router)();
// Users routes
/**
 * @swagger
 * /users:
 *   get:
 *     summary: Obtiene todos los usuarios.
 *     description: Retorna una lista de todos los usuarios disponibles.
 *     responses:
 *       200:
 *         description: Éxito. Retorna la lista de usuarios.
 *       500:
 *         description: Error interno del servidor.
 */
router.get('/', users_controllers_1.getUsersController);
/**
 * @swagger
 * /users/{id}:
 *   get:
 *     summary: Actualiza un usuario existente.
 *     description: Actualiza un usuario existente con la información proporcionada.
 *     parameters:
 *       - in: get
 *         name: id
 *         required: true
 *         description: ID del usuario que se va a obtener.
 *         schema:
 *           type: string
 *
 *     responses:
 *       200:
 *         description: usuario actualizado exitosamente.
 *       400:
 *         description: La solicitud es incorrecta o incompleta.
 *       404:
 *         description: usuario no encontrado.
 *       500:
 *         description: Error interno del servidor.
 */
router.get('/:id', users_controllers_1.getUserByIdController);
/**
 * @swagger
 * /users:
 *   post:
 *     summary: Crea un nuevo usuario.
 *     description: Crea un nuevo usuario con la información proporcionada.
 *     requestBody:
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             type: object
 *             required:
 *               - id
 *               - name
 *             properties:
 *               id:
 *                 type: string
 *                 description: ID del usuario.
 *               name:
 *                 type: string
 *                 description: Nombre del usuario.
 *     responses:
 *       201:
 *         description: usuario creado exitosamente.
 *       400:
 *         description: La solicitud es incorrecta o incompleta.
 *       500:
 *         description: Error interno del servidor.
 */
router.post('/', users_validations_1.validateUser, users_controllers_1.createUserController);
/**
 * @swagger
 * /users/{id}:
 *   put:
 *     summary: Actualiza un usuario existente.
 *     description: Actualiza un usuario existente con la información proporcionada.
 *     parameters:
 *       - in: put
 *         name: id
 *         required: true
 *         description: ID del usuario que se va a actualizar.
 *         schema:
 *           type: string
 *     requestBody:
 *       required: true
 *       content:
 *         application/json:
 *
 *     responses:
 *       200:
 *         description: usuario actualizado exitosamente.
 *       400:
 *         description: La solicitud es incorrecta o incompleta.
 *       404:
 *         description: usuario no encontrado.
 *       500:
 *         description: Error interno del servidor.
 */
router.put('/:id', users_validations_1.validateUser, users_controllers_1.updateUserController);
/**
 * @swagger
 * /users/{id}:
 *   delete:
 *     summary: Elimina un usuario existente.
 *     description: Elimina un usuario existente según su ID.
 *     parameters:
 *       - in: path
 *         name: id
 *         required: true
 *         description: ID del usuario que se va a eliminar.
 *         schema:
 *           type: string
 *     responses:
 *       204:
 *         description: usuario eliminado exitosamente.
 *       404:
 *         description: usuario no encontrado.
 *       500:
 *         description: Error interno del servidor.
 */
router.delete('/:id', users_controllers_1.deleteUserController);
exports.default = router;
