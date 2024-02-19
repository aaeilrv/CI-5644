'use client';
import { useUser } from "@auth0/nextjs-auth0/client";
import Image from "next/image";

export default function Profile() {
  const { user, isLoading } = useUser();
  if (isLoading) return <div>Loading...</div>;

  return (
    <div className="flex w-full h-full rounded-lg bg-[#d6dfea] p-4 drop-shadow-md">
      <h1>Página en construcción</h1>
    </div>
    
  );
}