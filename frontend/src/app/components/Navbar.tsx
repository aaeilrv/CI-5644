'use client';

import { useState } from "react"
import { BookOpenIcon } from "@heroicons/react/16/solid"
import { Bars3Icon } from "@heroicons/react/16/solid"
import Link from "next/link";

export default function Navbar() {
  const [open, setOpen] = useState(false)
  return (
    <nav className="bg-transparent fixed w-full z-30 top-0 start-0">
      <div className="px-10 py-2 bg-white bg-opacity-50">
        <div className="grid grid-cols-2 md:grid-cols-3 place-content-between py-2 items-center">
          {/* Title */}
          <Link href="#home" className="flex">
            <BookOpenIcon className="w-8"/>
            <div className="font-bold text-2xl">Barajitas</div>
          </Link>

          {/*Link*/}
          <div className="hidden md:flex justify-center space-x-5 font-medium">
            <Link href="#faq" className="mr-4">FAQ</Link>
            <Link href="#testimonials" className="mr-4">Testimonios</Link>
            <Link href="#contact" className="mr-4">Contacto</Link>
          </div>

          {/*Login*/}
          <div>
          <div className="flex justify-end">
            <a href="#" className="hidden md:flex font-bold text-xl">Entrar</a>
          </div>

          {/* Mobile */}
          <div className="flex justify-end">
            <button onClick={() => setOpen(!open)}>
              <Bars3Icon className="md:hidden md: w-8"/>
            </button>
          </div>
          </div>
        </div>
      </div>
      { open &&
      <div className="flex justify-end p-2 md:hidden">
        <div className="bg-white px-2 py-2 bg-opacity-50 rounded-xl text-right">
          <Link href="#faq" className="block py-2 hover:bg-white hover:bg-opacity-30 px-4 rounded-xl">FAQ</Link>
          <Link href="#testimonials" className="block py-2 hover:bg-white hover:bg-opacity-30 px-4 rounded-xl">Testimonios</Link>
          <Link href="#contact" className="block py-2 hover:bg-white hover:bg-opacity-30 px-4 rounded-xl">Contacto</Link>
          <a href="#" className="block py-2 font-bold text-xl px-4 rounded-xl hover:bg-white hover:bg-opacity-50">Entrar</a>
        </div>
      </div>
      }
    </nav>
  )
}