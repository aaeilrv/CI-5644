'use client';
import { useUser } from "@auth0/nextjs-auth0/client";
import Image from "next/image";
import Link from "next/link";

export default function Profile() {
  const { user, isLoading } = useUser();
  if (isLoading) return <div>Loading...</div>;

  return (

    <div className="flex w-full h-full rounded-lg bg-[#d6dfea] p-4 drop-shadow-md">
        <div className="bg-white p-2 rounded-lg w-48">
          <Image src="https://i.pinimg.com/564x/4b/9b/14/4b9b1469f4246f41384258c827d1d445.jpg" alt="" width={1080} height={1080} loading="lazy" />
        </div>
        <div className="p-4 space-y-10">
          <div className="flex flex-wrap">
            <h1 className="flex-auto font-bold text-slate-900 text-5xl">
              {user?.nickname}
            </h1>
            <div className="w-full flex-none mt-2 order-1 space-y-4 text-3xl font-bold text-violet-600">
              10/10 barajitas coleccionadas
            </div>
            <div className="text-sm font-medium text-slate-500"> 
              11/07/00
            </div>
          </div>

          <div className="flex space-x-4 mb-10 text-sm font-medium">
            <div className="flex-auto flex space-x-4">
            <p className="text-lg font-semibold">Correo electrónico: </p>
            <p className="text-lg">{user?.email}</p>
            </div>
          </div>
          {/* Agrega el botón que redirige a /editProfile */}
          <Link href="/editProfile">
            <button className="bg-violet-400 text-white px-2 py-1 rounded-md hover:bg-violet-500 mt-2">
              Editar Perfil
            </button>
          </Link>
        </div>
      </div>
    
  );
}