"use client";

import { StarIcon } from "@heroicons/react/16/solid"

export default function Landing() {
  return (
    <div className='grid grid-cols-12 gap-4 h-screen bg-[#75aadb] relative z-10'>
      <div className="z-20 absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 text-center text-xl py-40 justify-center">
        <div className="grid justify-items-center mt-4 gap-8">
          <div className="w-1/2 text-[#FCBF45]">
            <StarIcon className="w-10 sm:w-16 mx-auto"/>
            <div className="flex justify-center">
              <StarIcon className="w-10 sm:w-16 mx-auto mb-5 rotate-45"/>
              <StarIcon className="w-10 sm:w-16 mx-auto mb-5 -rotate-45"/>
            </div>
          </div>
          <div className="align-center font-bold text-white">
            <h1 className="text-4xl sm:text-5xl uppercase">Intercambia tus barajitas</h1>
            <h2 className="text-4xl sm:text-5xl">conoce a otros fanáticos</h2>
          </div>
          <button className="bg-[#FCBF45] py-3 px-4 font-semibold rounded-xl w-1/8">
            <a href="/#">Regístrate</a>
          </button>
        </div>
      </div>
      {/* Background */}
      {
        Array(12).fill(0).map((_, i) => (
          <div key={i} className='relative'>
            <div className="w-full h-screen bg-[#83b2df]"></div>
          </div>
        ))
      }
    </div>
  )
}