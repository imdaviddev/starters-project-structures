import swaggerJsdoc from 'swagger-jsdoc';


const options = {
    definition: {
        openapi: '3.0.0',
        info: {
            title: 'API de Gestión de Roles',
            version: '1.0.0',
            description: 'Una API para gestionar roles en una aplicación',
        },
        servers: [
            {
                url: 'http://localhost:3000',
            },
        ],
    },
    apis: ['./src/routes/*.ts','./src/controllers/*.ts'], // Ruta a tus controladores
};

const specs = swaggerJsdoc(options);

module.exports = specs;