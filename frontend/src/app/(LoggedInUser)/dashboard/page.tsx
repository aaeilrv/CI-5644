"use client";
import { useUser } from "@auth0/nextjs-auth0/client";
import Navbar from "../../components/Navbar";

const navbar = [
  {
    href: "/album",
    text: "Tu álbum",
  },
  {
    href: "/exchange",
    text: "Intercambio",
  },
  {
    href: "/buy",
    text: "Compra de barajitas",
  },
  {
    href: "/profile",
    text: "Perfil",
  },
];

export default function Dashboard() {
  const { user, isLoading } = useUser();

  if (isLoading) return <div>Loading...</div>;

  return (
    <>
      <Navbar navigation_links={navbar}/>
      <div className="py-16 px-8">
        Probando el sistema de routing nuevo de nextjs. Esto es el dashboard (?)
        {user && <p>Usuario: {user.name}</p>}
      </div>
    </>
  );
}
