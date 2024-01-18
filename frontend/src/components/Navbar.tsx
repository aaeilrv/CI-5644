import { BookOpenIcon } from "@heroicons/react/16/solid"

const Navbar = () => {
  return (
    <nav className="bg-white fixed w-full z-20 top-0 start-0 bg-opacity-50">
      <div className="mx-auto max-w-screen-xl px-10 py-2">
        <div className="flex justify-between py-2 items-center">
          <a href="#" className="flex">
            <BookOpenIcon className="w-8"/>
            <div className="font-bold text-2xl">Barajitas</div>
          </a>
          <div className="flex items-center space-x-5 font-medium">
            <a href="/" className="mr-4">FAQ</a>
            <a href="#" className="mr-4">Contacto</a>
            <a href="#" className="mr-4">Álbumes Disponibles</a>
          </div>
          <a className="font-bold text-xl" href="#">Entrar</a>
        </div>
      </div>
    </nav>
  )
}

export default Navbar