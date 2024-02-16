"use client";
import { useUser } from "@auth0/nextjs-auth0/client";
import Link from "next/link";
import Navbar from "../components/Navbar";

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

export default function Barajitas() {
  const { user, isLoading } = useUser();
  if (isLoading) return <div>Loading...</div>;

  return (
    <div className="bg-[#75aadb] h-full w-full overflow-hidden">
        owo
    </div>
  );
}
