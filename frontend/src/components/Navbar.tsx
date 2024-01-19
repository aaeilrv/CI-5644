import { useState } from "react"
import { BookOpenIcon } from "@heroicons/react/16/solid"
import { Bars3Icon } from "@heroicons/react/16/solid"

export default function Navbar() {
  const [open, setOpen] = useState(false)
  return (
    <nav className="bg-transparent fixed w-full z-30 top-0 start-0">
      <div className="px-10 py-2 bg-white bg-opacity-50">
        <div className="flex justify-between py-2 items-center">
          {/* Title */}
          <a href="/" className="flex">
            <BookOpenIcon className="w-8"/>
            <div className="font-bold text-2xl">Barajitas</div>
          </a>
          {/* Links */}
          <div className="hidden md:flex items-center space-x-5 font-medium">
            <a href="#" className="mr-4">FAQ</a>
            <a href="#" className="mr-4">Contacto</a>
            <a href="#" className="mr-4">Álbumes Disponibles</a>
          </div>
          {/* Login */}
          <div className="flex">
          <a href="#" className="hidden md:flex font-bold text-xl">Entrar</a>

          {/* Mobile */}
          <button onClick={() => setOpen(!open)}>
            <Bars3Icon className="sm:hidden md: w-8"/>
          </button>
          </div>
        </div>
      </div>
      { open &&
      <div className="flex justify-end p-2 md:hidden">
        <div className="bg-white px-2 py-2 bg-opacity-50 rounded-xl text-right">
          <a href="#" className="block py-2 hover:bg-white hover:bg-opacity-30 px-4 rounded-xl">FAQ</a>
          <a href="#" className="block py-2 hover:bg-white hover:bg-opacity-30 px-4 rounded-xl">Contacto</a>
          <a href="#" className="block py-2 hover:bg-white hover:bg-opacity-30 px-4 rounded-xl">Álbumes<br/>Disponibles</a>
          <a href="#" className="block py-2 font-bold text-xl px-4 rounded-xl hover:bg-white hover:bg-opacity-50">Entrar</a>
        </div>
      </div>
      }
    </nav>
  )
}