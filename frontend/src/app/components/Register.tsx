"use client";
import React, { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { z } from "zod";

type RegisterValues = {
  username: string;
  name: string;
  email: string;
  password: string;
  confirmPassword: string;


};

// Prondodo react-hook-form con zod
const registerSchema = {
  username: z.string().min(4,{message: "El nombre de usuario es requerido"}).max(30),
  name: z.string().min(4).max(30),
  email: z.string().email().min(4).max(250),
  password: z.string().min(4,{message: "La contraseña debe tener al menos 4 caracteres"}).max(25),
  confirmPassword: z.string().min(4).max(25),
}

export const Register = () => {
  const [passwordEye, setPasswordEye] = useState(false);
  const [confirmPasswordEye, setConfirmPasswordEye] = useState(false);

  // const [emialDefaultVal, setEmialDefaultVal] = useState('')
  // const [passwordDefaultVal, setPasswordDefaultVal] = useState('')

  const {
    register,
    handleSubmit,
    watch,
    formState: { errors, isValid, isDirty },
  } = useForm<RegisterValues>({
    mode: "onChange",
  });

  // useEffect(() => {
  //   setEmialDefaultVal(sessionStorage.getItem("email") || '')
  //   setPasswordDefaultVal(sessionStorage.getItem("password") || '')
  //   reset({
  //     email: emialDefaultVal,
  //     password: passwordDefaultVal
  //   })
  // }, [reset])

  const onSubmit = handleSubmit(async (data) => {
    const loginBody = {
      email: data.email,
      psw: data.password,
    };

    console.log({ loginBody });

    // const loginResponse = await axiosHookWithoutToken(
    //   "post",
    //   "signin/admins",
    //   loginBody,
    // );

    // if (loginResponse.status != "success") {
    //   console.log(loginResponse.message);
    //   return;
    // } else {
    //   const user = loginResponse.response.data.data;

    //   setUserValues(user);

    //   await setExchangeRate();

    //   setUser(data.email);
    //   sessionStorage.setItem("email", data.email);

    //   navigate("/main");
    // }
  });

  const handlePasswordEye = () => {
    setPasswordEye(!passwordEye);
  };

  const handleConfirmPasswordEye = () => {
    setConfirmPasswordEye(!confirmPasswordEye);
  };

  return (
    <div className="flex  sm:w-full md:w-4/6 justify-center py-32 sm:py-24 md:py-24 lg:py-24  ">
      <div className="bg-[#4DBDEB] w-full  sm:w-2/5 md:w-6/12 flex flex-col rounded-2xl">
        {/* header or title */}
        <div>
          <h1 className="text-4xl text-white font-bold text-center mt-12">
            Registrate
          </h1>
        </div>
        {/* Form */}

        <div className="flex justify-center">
          <form className="flex flex-col w-2/3" onSubmit={onSubmit}>
          <div className="flex flex-col mt-12">
              <label className="text-white font-semibold text-lg">Nombre de usuario</label>

              <input
                type="text"
                className="bg-transparent border-b-2  outline-none text-white"
                {...register("username", {
                  required: {
                    value: true,
                    message: "El nombre de usuario es requerido",
                  },
                  pattern: {
                    value: /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i,
                    message: "El nombre de usuario no es valido",
                  },
                  minLength: {
                    value: 4,
                    message:
                      "El nombre de usuario debe tener al menos 4 caracteres",
                  },
                  maxLength: {
                    value: 30,
                    message:
                      "El nombre de usuario debe tener menos de 30 caracteres",
                  },
                })}
              ></input>
              {errors.username && (
                <span className="text-red-500 text-sm">
                  {errors.username.message}
                </span>
              )}
            </div>
            <div className="flex flex-col mt-12">
              <label className="text-white font-semibold text-lg">Nombre</label>

              <input
                type="text"
                className="bg-transparent border-b-2  outline-none text-white"
                {...register("name", {
                  required: {
                    value: true,
                    message: "El nombre es requerido",
                  },
                  pattern: {
                    value: /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i,
                    message: "El nombre no es valido",
                  },
                  minLength: {
                    value: 4,
                    message:
                      "El nombre debe tener al menos 4 caracteres",
                  },
                  maxLength: {
                    value: 30,
                    message:
                      "El nombre debe tener menos de 30 caracteres",
                  },
                })}
              ></input>
              {errors.name && (
                <span className="text-red-500 text-sm">
                  {errors.name.message}
                </span>
              )}
            </div>
            <div className="flex flex-col mt-12">
              <label className="text-white font-semibold text-lg">Correo</label>

              <input
                type="text"
                className="bg-transparent border-b-2  outline-none text-white"
                {...register("email", {
                  required: {
                    value: true,
                    message: "El correo electrónico es requerido",
                  },
                  pattern: {
                    value: /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i,
                    message: "El correo electrónico no es valido",
                  },
                  minLength: {
                    value: 4,
                    message:
                      "El correo electrónico debe tener al menos 4 caracteres",
                  },
                  maxLength: {
                    value: 250,
                    message:
                      "El correo electronico debe tener menos de 250 caracteres",
                  },
                })}
              ></input>
              {errors.email && (
                <span className="text-red-500 text-sm">
                  {errors.email.message}
                </span>
              )}
            </div>

            <div className="flex flex-col mt-12">
              <label className="text-white font-semibold text-lg">
                Contraseña
              </label>
              {/* input tipo password con boton de ojo */}
              {passwordEye ? (
                <div className="flex">
                  <input
                    type="text"
                    className="bg-transparent border-b-2 border-white outline-none text-white w-full"
                    {...register("password", {
                      required: {
                        value: true,
                        message: "La contraseña es requerida",
                      },
                      minLength: {
                        value: 4,
                        message:
                          "La contraseña debe tener al menos 4 caracteres",
                      },
                      maxLength: {
                        value: 25,
                        message:
                          "La contraseña debe tener menos de 25 caracteres",
                      },
                    })}
                  ></input>

                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    viewBox="0 0 24 24"
                    fill="currentColor"
                    onClick={handlePasswordEye}
                    className="w-6 border-white border-b-2  text-lightGrey"
                  >
                    <path d="M3.53 2.47a.75.75 0 0 0-1.06 1.06l18 18a.75.75 0 1 0 1.06-1.06l-18-18ZM22.676 12.553a11.249 11.249 0 0 1-2.631 4.31l-3.099-3.099a5.25 5.25 0 0 0-6.71-6.71L7.759 4.577a11.217 11.217 0 0 1 4.242-.827c4.97 0 9.185 3.223 10.675 7.69.12.362.12.752 0 1.113Z" />
                    <path d="M15.75 12c0 .18-.013.357-.037.53l-4.244-4.243A3.75 3.75 0 0 1 15.75 12ZM12.53 15.713l-4.243-4.244a3.75 3.75 0 0 0 4.244 4.243Z" />
                    <path d="M6.75 12c0-.619.107-1.213.304-1.764l-3.1-3.1a11.25 11.25 0 0 0-2.63 4.31c-.12.362-.12.752 0 1.114 1.489 4.467 5.704 7.69 10.675 7.69 1.5 0 2.933-.294 4.242-.827l-2.477-2.477A5.25 5.25 0 0 1 6.75 12Z" />
                  </svg>
                </div>
              ) : (
                <div className="flex">
                  <input
                    type="password"
                    className="bg-transparent border-b-2 border-white outline-none text-white w-full"
                    {...register("password", {
                      required: {
                        value: true,
                        message: "La contraseña es requerida",
                      },
                      minLength: {
                        value: 4,
                        message:
                          "La contraseña debe tener al menos 4 caracteres",
                      },
                      maxLength: {
                        value: 25,
                        message:
                          "La contraseña debe tener menos de 25 caracteres",
                      },
                    })}
                  ></input>

                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    viewBox="0 0 24 24"
                    fill="currentColor"
                    className="w-6   border-white border-b-2 text-lightGrey"
                    onClick={handlePasswordEye}
                  >
                    <path d="M12 15a3 3 0 100-6 3 3 0 000 6z" />
                    <path
                      fillRule="evenodd"
                      d="M1.323 11.447C2.811 6.976 7.028 3.75 12.001 3.75c4.97 0 9.185 3.223 10.675 7.69.12.362.12.752 0 1.113-1.487 4.471-5.705 7.697-10.677 7.697-4.97 0-9.186-3.223-10.675-7.69a1.762 1.762 0 010-1.113zM17.25 12a5.25 5.25 0 11-10.5 0 5.25 5.25 0 0110.5 0z"
                      clipRule="evenodd"
                    />
                  </svg>
                </div>
              )}

              {errors.password && (
                <span className="text-red-500 text-sm">
                  {errors.password.message}
                </span>
              )}
            </div>

            <div className="flex flex-col mt-12">
              <label className="text-white font-semibold text-lg">
                Confirmar contraseña
              </label>
              {/* input tipo password con boton de ojo */}
              {confirmPasswordEye ? (
                <div className="flex">
                  <input
                    type="text"
                    className="bg-transparent border-b-2 border-white outline-none text-white w-full"
                    {...register("confirmPassword", {
                      required: {
                        value: true,
                        message: "La contraseña es requerida",
                      },
                      minLength: {
                        value: 4,
                        message:
                          "La contraseña debe tener al menos 4 caracteres",
                      },
                      maxLength: {
                        value: 25,
                        message:
                          "La contraseña debe tener menos de 25 caracteres",
                      },
                      validate: (val: string) => {
                        if (watch("password") != val)
                          return "Las contraseñas no coinciden";
                      },
                    })}
                  ></input>

                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    viewBox="0 0 24 24"
                    fill="currentColor"
                    onClick={handleConfirmPasswordEye}
                    className="w-6 border-white border-b-2  text-lightGrey"
                  >
                    <path d="M3.53 2.47a.75.75 0 0 0-1.06 1.06l18 18a.75.75 0 1 0 1.06-1.06l-18-18ZM22.676 12.553a11.249 11.249 0 0 1-2.631 4.31l-3.099-3.099a5.25 5.25 0 0 0-6.71-6.71L7.759 4.577a11.217 11.217 0 0 1 4.242-.827c4.97 0 9.185 3.223 10.675 7.69.12.362.12.752 0 1.113Z" />
                    <path d="M15.75 12c0 .18-.013.357-.037.53l-4.244-4.243A3.75 3.75 0 0 1 15.75 12ZM12.53 15.713l-4.243-4.244a3.75 3.75 0 0 0 4.244 4.243Z" />
                    <path d="M6.75 12c0-.619.107-1.213.304-1.764l-3.1-3.1a11.25 11.25 0 0 0-2.63 4.31c-.12.362-.12.752 0 1.114 1.489 4.467 5.704 7.69 10.675 7.69 1.5 0 2.933-.294 4.242-.827l-2.477-2.477A5.25 5.25 0 0 1 6.75 12Z" />
                  </svg>
                </div>
              ) : (
                <div className="flex">
                  <input
                    type="password"
                    className="bg-transparent border-b-2 border-white outline-none text-white w-full"
                    {...register("confirmPassword", {
                      required: {
                        value: true,
                        message: "La contraseña es requerida",
                      },
                      minLength: {
                        value: 4,
                        message:
                          "La contraseña debe tener al menos 4 caracteres",
                      },
                      maxLength: {
                        value: 25,
                        message:
                          "La contraseña debe tener menos de 25 caracteres",
                      },
                      validate: (val: string) => {
                        if (watch("password") != val)
                          return "Las contraseñas no coinciden";
                      },
                    })}
                  ></input>

                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    viewBox="0 0 24 24"
                    fill="currentColor"
                    className="w-6   border-white border-b-2 text-lightGrey"
                    onClick={handleConfirmPasswordEye}
                  >
                    <path d="M12 15a3 3 0 100-6 3 3 0 000 6z" />
                    <path
                      fillRule="evenodd"
                      d="M1.323 11.447C2.811 6.976 7.028 3.75 12.001 3.75c4.97 0 9.185 3.223 10.675 7.69.12.362.12.752 0 1.113-1.487 4.471-5.705 7.697-10.677 7.697-4.97 0-9.186-3.223-10.675-7.69a1.762 1.762 0 010-1.113zM17.25 12a5.25 5.25 0 11-10.5 0 5.25 5.25 0 0110.5 0z"
                      clipRule="evenodd"
                    />
                  </svg>
                </div>
              )}

              {errors.confirmPassword && (
                <span className="text-red-500 text-sm">
                  {errors.confirmPassword.message}
                </span>
              )}
            </div>

            <div className="flex mt-16 justify-center mb-12">
              <button
                className="bg-[#FFE08C] disabled:opacity-50 text-white text-center rounded-lg py-3 md:p-3 w-1/2"
                disabled={!isDirty || !isValid}
              >
                Registrate
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};
