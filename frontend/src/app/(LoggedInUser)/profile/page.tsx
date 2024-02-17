'use client';
import { useUser } from "@auth0/nextjs-auth0/client";

export default function Profile() {
  const { user, isLoading } = useUser();
  if (isLoading) return <div>Loading...</div>;

  return (
  <div className="w-full h-full rounded-lg bg-[#d6dfea] p-2 drop-shadow-md">
    <div className="p-4">
      <h1 className="text-2xl font-bold">Perfil</h1>
      <p className="text-lg">{user?.name}</p>
      <p className="text-lg">Correo electrónico: {user?.email}</p>
    </div>
  </div>
  );
}
