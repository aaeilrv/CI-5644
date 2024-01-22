"use client";
import Image from "next/image";


export default function Testimonials() {
  return (
    <div className='bg-[#202437] w-full h-screen flex items-center justify-center space-x-10 overflow-hidden'>

      <div className="grid grid-cols-1 justify-items-center">
        <div className="text-white">
          <h1 className="font-bold text-4xl sm:text-5xl uppercase">Testimonios</h1>
        </div>

        {/* Comentarios */}
        <div className="grid grid-col-1 md:flex space-x-10">
          <div className="block max-w-sm p-6 border-8 border-[#ce1939] rounded-lg mt-10">
            <h5 className="mb-2 text-2xl font-bold italic text-white">"Bslablabla"</h5>
            <p className="font-normal text-white text-left">-Pepito</p>
          </div>

          <div className="block max-w-sm p-6 border-8 border-[#ce1939] rounded-lg mt-10">
            <h5 className="mb-2 text-2xl font-bold italic text-white">"Blablabla"</h5>
            <p className="font-normal text-white text-left">-Pepito</p>
          </div>

          <div className="block max-w-sm p-6 border-8 border-[#ce1939] rounded-lg mt-10">
            <h5 className="mb-2 text-2xl font-bold italic text-white">"Blablabla"</h5>
            <p className="font-normal text-white text-left">-Pepito</p>
          </div>
        </div>
      </div>

      {/* Barajitas */}
      {/*
      <div className="hidden md:flex">
        <div className="relative">
          <div className="absolute p-2 bg-white rounded-lg shadow z-50">
            <Image src="/static/images/cristiano_barajita.png" width={200} height={40} alt={"Cristiano"}/>
          </div>
          <div className="absolute top-0 left-0 bg-white rounded-lg p-2 ml-16 z-40" style={{ transform: 'rotate(20deg)' }}>
            <Image src="/static/images/messi_barajita.png" width={200} height={40} alt={"Messi"}/>
          </div>
          <div className="top-0 left-0 bg-white rounded-lg p-2 ml-24 z-30" style={{ transform: 'rotate(45deg)' }}>
            <Image src="/static/images/mbappe_barajita.png" width={200} height={40} alt={"Mbappe"}/>
          </div>
        </div>
      </div>
      */}
    </div>
  )
}
