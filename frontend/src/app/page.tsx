import Navbar from "@/components/Navbar"
import { StarIcon } from "@heroicons/react/16/solid"

export default function Home() {
  return (
    <div className='grid grid-cols-12 gap-4 h-max bg-[#75aadb] relative z-10'>
      <Navbar/>
      <div className="absolute top-0 left-0 right-0 bottom-0 text-center z-20 text-xl py-40 justify-center">
        <div className="grid justify-items-center mt-4 gap-8">
          <div className="w-1/4 text-[#FCBF45]">
            <StarIcon className="w-16 mx-auto"/>
            <div className="flex justify-center">
              <StarIcon className="w-16 mx-auto mb-5 rotate-45"/>
              <StarIcon className="w-16 mx-auto mb-5 -rotate-45"/>
            </div>
          </div>
          <div className="align-center font-bold text-white">
            <h1 className="text-5xl uppercase">Intercambia tus barajitas</h1>
            <h2 className="text-5xl">conoce a otros fanáticos</h2>
          </div>
          <button className="bg-[#FCBF45] py-3 px-4 font-semibold rounded-xl">
            <a href="/#">Regístrate</a>
          </button>
        </div>
      </div>
      {/* Background */}
      {
        Array(12).fill(0).map((_, i) => (
          <div key={i} className='relative'>
            <div className="w-full h-dvh bg-[#83b2df]"></div>
          </div>
        ))
      }
    </div>
  )
}