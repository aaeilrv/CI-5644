"use client";
import React, { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import { useRouter } from 'next/navigation';
import axios from "axios";

const NEXT_AUTH_API_REGISTER_URL = `${process.env.NEXT_PUBLIC_USER_API_URL}`;

type RegisterValues = {
  username: string;
  name: string;
  email: string;
  birthday: string;
};

// Prondodo react-hook-form con zod
const registerSchema = z
  .object({
    username: z
      .string()
      .min(4, {
        message: "El nombre de usuario debe tener al menos 4 caracteres",
      })
      .max(30, {
        message: "El nombre de usuario debe tener menos de 30 caracteres.",
      }),
    name: z
      .string()
      .min(1, {
        message: "El campo no puede ser vacío.",
      })
      .max(30, {
        message: "Longitud máxima de 30 caracteres.",
      }),
    lastname: z
      .string()
      .min(1, {
        message: "El campo no puede ser vacío."
      })
      .max(30, {
        message: "Longitud máxima de 30 caracteres."
      }),
    email: z
      .string()
      .email({ message: "El correo electrónico no es valido." })
      .min(4, {
        message: "El correo electrónico debe tener al menos 4 caracteres.",
      })
      .max(250, {
        message: "El correo electronico debe tener menos de 250 caracteres.",
      }),
    birthday: z
      .string()
      .regex(/^\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$/, {
        message: "La fecha debe seguir el formato YYYY-MM-DD."
      })
  })

type registerSchemaType = z.infer<typeof registerSchema>;

export const Register = () => {
  const [passwordEye, setPasswordEye] = useState(false);
  const [confirmPasswordEye, setConfirmPasswordEye] = useState(false);
  const router = useRouter();

  const {
    register,
    handleSubmit,
    watch,
    formState: { errors, isValid, isDirty },
  } = useForm<registerSchemaType>({
    resolver: zodResolver(registerSchema),
    mode: "onChange",
  });

  const onSubmit = handleSubmit(async (data) => {
    const loginBody = {
      username: data.username,
      firstName: data.name,
      lastName: data.lastname,
      emailAddress: data.email,
      birthDay: data.birthday,
    };

    const response = await axios.get('/api/auth/token/');
    const { token } = response.data;
    const signupRes = await axios.post(
      NEXT_AUTH_API_REGISTER_URL,
      loginBody,
      {
        headers: {
          Authorization: `Bearer ${token}`
        }
      }
    );

    if (signupRes.status == 200) {
      router.push('/album');
    } else {
      alert("Ocurrió un error en el registro de sus datos. Por favor intente de nuevo");
    }
  });

  const handlePasswordEye = () => {
    setPasswordEye(!passwordEye);
  };

  const handleConfirmPasswordEye = () => {
    setConfirmPasswordEye(!confirmPasswordEye);
  };

  return (
    <div className="w-full h-1/2 flex items-center justify-center py-8">
      <div className="border-[#c7d3e1] bg-[#d6dfea] w-1/3 h-4/5 rounded-2xl">
        {/* header or title */}
        <div>
          <h1 className="text-3xl font-bold text-center mt-12">
            Completa tu información
          </h1>
        </div>
        {/* Form */}

        <div className="flex justify-center mt-10">
          <form className="flex flex-col w-2/3" onSubmit={onSubmit}>
            <div id="username-container" className="flex flex-col">
              <label className=" font-semibold text-lg">
                Nombre de usuario
              </label>
              <input
                id="username-input"
                type="text"
                className="bg-transparent border-b-2 outline-none px-2 py-1"
                {...register("username")}
              ></input>
              {errors.username && (
                <span className="text-red-500 text-sm">
                  {errors.username.message}
                </span>
              )}
            </div>
            <div id="firstName-container" className="flex flex-col mt-4">
              <label className=" font-semibold text-lg">
                Nombre
              </label>
              <input
                id="firstName-input"
                type="text"
                className="bg-transparent border-b-2 outline-none px-2 py-1"
                {...register("name")}
              ></input>
              {errors.name && (
                <span className="text-red-500 text-sm">
                  {errors.name.message}
                </span>
              )}
            </div>
            <div id="lastName-container" className="flex flex-col mt-4">
              <label className=" font-semibold text-lg">
                Apellido
              </label>
              <input
                id="lastName-input"
                type="text"
                className="bg-transparent border-b-2 outline-none px-2 py-1"
                {...register("lastname")}
              ></input>
              {errors.lastname && (
                <span className="text-red-500 text-sm">
                  {errors.lastname.message}
                </span>
              )}
            </div>
            <div id="email-container" className="flex flex-col mt-4">
              <label className=" font-semibold text-lg">
                Correo
              </label>
              <input
                id="email-input"
                type="text"
                className="bg-transparent border-b-2 outline-none px-2 py-1"
                {...register("email")}
              ></input>
              {errors.email && (
                <span className="text-red-500 text-sm">
                  {errors.email.message}
                </span>
              )}
            </div>
            <div id="birthday-container" className ="flex flex-col mt-4">
              <label className = " font-semibold text-lg">
                Fecha de Nacimiento
              </label>
              <input
                id="birthday-input"
                type="text"
                placeholder="YYYY-MM-DD"
                className="bg-transparent border-b-2 outline-none px-2 py-1"
                {...register("birthday")}
                ></input>
                {errors.birthday && (
                  <span className="text-red-500 text-sm">
                    {errors.birthday.message}
                  </span>
                )}
            </div>
            <div className="flex mt-16 justify-center mb-12">
              <button
                className="bg-[#FCBF45] disabled:opacity-50 text-center rounded-lg py-3 w-1/2 font-bold"
                disabled={!isDirty || !isValid}
              >
                Regístrate
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};
