'use client';
import { useUser } from "@auth0/nextjs-auth0/client";
import Image from "next/image";

export default function Profile() {
  const { user, isLoading } = useUser();
  if (isLoading) return <div>Loading...</div>;

  return (

    <div className="flex font-sans">
      <div className="flex w-full h-full rounded-lg bg-[#d6dfea] p-4 drop-shadow-md">
        <div className="bg-white p-2 rounded-lg w-1/4">
          <Image src="https://pbs.twimg.com/media/GGiSlEFbUAAsh7p?format=jpg&name=900x900" alt="" width={1080} height={1080} loading="lazy" />
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

          <div className="flex space-x-4 mb-5 text-sm font-medium">
            <div className="flex-auto flex space-x-4">
            <p className="text-lg font-semibold">Correo electrónico: </p>
            <p className="text-lg">{user?.email}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
    
  );
}