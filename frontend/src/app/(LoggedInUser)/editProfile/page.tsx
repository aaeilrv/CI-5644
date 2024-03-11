'use client';
import React, { useState } from 'react';
import { useUser } from "@auth0/nextjs-auth0/client";
import axios from 'axios';
import getJwt from "@/app/helpers/getJwtClient";

interface EditUser {
    firstName: string;
    lastName: string;
    username: string;
    email: string;
}

export default function Profile() {
    const { user, isLoading } = useUser();
    const [editUser, setEditUser] = useState<EditUser>({ firstName: '', lastName: '', username: '', email: '' });
    const [message, setMessage] = useState(''); // Nuevo estado para el mensaje

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setEditUser({ ...editUser, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        const { token } = await getJwt();

        const response = await fetch('http://localhost:8070/v1/user/edit', {
            method: 'PUT',
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            },
            body: JSON.stringify(editUser)
        });

        if (response.ok) {
            const data = await response.json();
            console.log('User updated successfully:', data);
            setEditUser({ firstName: '', lastName: '', username: '', email: '' }); // Limpia el formulario
            setMessage('¡Cambios guardados!'); // Muestra el mensaje
        } else {
            console.error('Error updating user:', response.status);
        }


    };

    if (isLoading) return <div>Loading...</div>;

    return (
        <div className="flex w-full h-full rounded-lg bg-[#d6dfea] p-4 drop-shadow-md">
            <div className="p-4">
                <h1 className="text-2xl font-bold mb-4">Editar Perfil</h1>

                <div style={{ display: 'flex', justifyContent: 'center' }}>
                    <form onSubmit={handleSubmit} className="flex flex-col max-w-md">
                        <label className="flex flex-col mb-4 w-full">
                            <span className="mb-2">Nombre:</span>
                            <input type="text" name="firstName" value={editUser.firstName} onChange={handleChange} className="p-2 border rounded-md w-full flex-1" />
                        </label>
                        <label className="flex flex-col mb-4 w-full">
                            <span className="mb-2">Apellido:</span>
                            <input type="text" name="lastName" value={editUser.lastName} onChange={handleChange} className="p-2 border rounded-md w-full flex-1" />
                        </label>
                        <label className="flex flex-col mb-4 w-full">
                            <span className="mb-2">Nombre de usuario:</span>
                            <input type="text" name="username" value={editUser.username} onChange={handleChange} className="p-2 border rounded-md w-full flex-1" />
                        </label>
                        <label className="flex flex-col mb-4 w-full">
                            <span className="mb-2">Correo electrónico:</span>
                            <input type="email" name="email" value={editUser.email} onChange={handleChange} className="p-2 border rounded-md w-full flex-1" />
                        </label>
                        <button type="submit" className="p-2 bg-violet-400 text-white rounded-md hover:bg-violet-500">Guardar cambios</button>
                        {message && <p>{message}</p>}
                    </form>
                </div>
            </div>
        </div>
    );
}
