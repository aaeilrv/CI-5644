"use client";
import { useUser } from "@auth0/nextjs-auth0/client";
import Link from "next/link";
import NavbarInApp from "../components/NavbarInApp";

export default function Dashboard() {
  const { user, isLoading } = useUser();

  if (isLoading) return <div>Loading...</div>;

  return (
    <>
      <NavbarInApp />
      <div className="py-16 px-8">
        Probando el sistema de routing nuevo de nextjs. Esto es el dashboard (?)
        {user && <p>Usuario: {user.name}</p>}
      </div>
    </>
  );
}
