"use client";
import { useUser } from "@auth0/nextjs-auth0/client";
import Link from "next/link";
import Navbar from "../components/Navbar";
import Image from "next/image";
import { ChevronLeftIcon, ChevronRightIcon } from "@heroicons/react/16/solid";
import { AdjustmentsHorizontalIcon } from "@heroicons/react/20/solid";

const navbar = [
  {
    href: "/album",
    text: "Álbum",
  },
  {
    href: "/exchange",
    text: "Intercambio",
  },
  {
    href: "/buy",
    text: "Compra",
  },
  {
    href: "/profile",
    text: "Perfil",
  },
];

const barajitas_temporal = [
  {
    id: 0,
    name: "Emiliano Martínez",
    player_number: 1,
    country: "Argentina",
    player_position: "Portero",
    photo: "https://i.pinimg.com/564x/2d/88/3b/2d883bddea69c947124837f48855a517.jpg"
  },
  {
    id: 1,
    name: "Lionel Messi",
    player_number: 10,
    country: "Argentina",
    player_position: "Delantero",
    photo: "https://i.pinimg.com/736x/f3/5b/fd/f35bfdfea8114351f2a262edc25561f3.jpg"
  },
  {
    id: 2,
    name: "Ángel Di María",
    player_number: 11,
    country: "Argentina",
    player_position: "Delantero",
    photo: "https://i.pinimg.com/564x/65/b4/8f/65b48f81da0b045856c1cf3bb71b24d9.jpg"
  },
  {
    id: 3,
    name: "Lautaro Martínez",
    player_number: 22,
    country: "Argentina",
    player_position: "Delantero",
    photo: "https://i.pinimg.com/564x/4c/66/cd/4c66cd446bb0776d0c77a4c9e1cfc226.jpg"
  },
  {
    id: 4,
    name: "Guido Rodríguez",
    player_number: 22,
    country: "Argentina",
    player_position: "Delantero",
    photo: "https://i.pinimg.com/564x/29/5f/58/295f58a9f811cabfe2f8370eb9e02be6.jpg"
  },
  {
    id: 5,
    name: "Nicolás Otamendi",
    player_number: 22,
    country: "Argentina",
    player_position: "Delantero",
    photo: "https://i.pinimg.com/564x/7f/1c/09/7f1c0979e6965b7a1fbc3f96a1a7006f.jpg"
  },
  {
    id: 6,
    name: "Nahuel Molina",
    player_number: 22,
    country: "Argentina",
    player_position: "Delantero",
    photo: "https://i.pinimg.com/564x/f5/dc/f2/f5dcf27a4d99b060d3f6219f1cdeeef2.jpg"
  },
  {
    id: 7,
    name: "Joaquín Correa",
    player_number: 22,
    country: "Argentina",
    player_position: "Delantero",
    photo: "https://i.pinimg.com/564x/66/c4/f8/66c4f8198ded2770cfa1f9a8b960bb30.jpg"
  },
  {
    id: 8,
    name: "Marcos Acuña",
    player_number: 22,
    country: "Argentina",
    player_position: "Delantero",
    photo: "https://i.pinimg.com/564x/9f/2a/60/9f2a604f43afa032beb18f045da0d5c4.jpg"
  },
  {
    id: 9,
    name: "Cristian Romero",
    player_number: 22,
    country: "Argentina",
    player_position: "Delantero",
    photo: "https://i.pinimg.com/564x/4b/9b/14/4b9b1469f4246f41384258c827d1d445.jpg"
  },
]

export default function Barajitas() {
  const { user, isLoading } = useUser();
  if (isLoading) return <div>Loading...</div>;

  return (
  <>
    <Navbar navigation_links={navbar} />
    <div className='w-full h-full flex flex-col items-center justify-center bg-gradient-to-b from-[#75aadb] to-[#4b749c]'>
      <div className="py-10 mt-14 w-full px-10 flex justify-between space-x-4 items-center">
        <button className="flex text-white space-x-2 ml-20">
          <AdjustmentsHorizontalIcon className="w-6 h-6 text-white"/>
          <p className="font-semibold">Filtrar</p>
          </button>
        <Link href="/compra" className="bg-[#FCBF45] py-3 px-4 font-semibold rounded-xl mr-4 mt-4">¡Compra barajitas!</Link>
      </div>

      {/* Barajitas */}
      <div className="w-11/12">
        <div className="w-11/12 mx-auto">
          <div className="grid grid-cols-5 gap-10">
            {barajitas_temporal.map((barajita, index) => (
              <div className="rounded-lg bg-white p-2 drop-shadow-md hover:bg-slate-300" key={index}>
                <Image src={barajita.photo} alt={barajita.name} className="w-full" width={1080} height={1080} />
              </div>
            ))}
          </div>
        </div>
      </div>

      {/* Pagination */}
      <div className="mb-4 mt-4 space-x-10">
        <button><ChevronLeftIcon className="w-10 h-10 text-white" /></button>
        <button><ChevronRightIcon className="w-10 h-10 text-white" /></button>
      </div>
    </div>
  </>
  );
}
