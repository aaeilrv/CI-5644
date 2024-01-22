"use client";

export default function Contact() {
  return (
    <div className='w-full h-screen flex items-center justify-center bg-gradient-to-bl from-[#600f22] from-50% to-[#540a1b] to-50%'>
      <div className="grid grid-cols-1 font-bold text-[#9e7c27] place-items-center px-4">
        <div className="flex justify-between py-2 mx-20 space-x-20">
          <a href="#" className="text-xl">IG</a>
          <a href="#" className="text-xl">TW</a>
          <a href="https://github.com/aaeilrv/CI5644-Barajitas" className="text-xl">GH</a>
        </div>
        <h2 className="text-xl font-normal text-center">CI5644: Herramientas de Programación</h2>
        <h2 className="text-xl font-normal">2024</h2>
      </div>
    </div>
  )
}