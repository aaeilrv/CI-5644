"use client";
import Image from "next/image";

const preguntas = [
  {
    pregunta: "¿Lorem ipsum dolor?",
    respuesta: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio."
  },
  {
    pregunta: "¿Lorem ipsum dolor sit amet?",
    respuesta: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet."
  },
  {
    pregunta: "¿Lorem ipsum dolor sit amet?",
    respuesta: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero."
  },
  {
    pregunta: "¿Lorem ipsum dolor?",
    respuesta: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio."
  },
  {
    pregunta: "¿Lorem ipsum dolor sit amet?",
    respuesta: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet."
  },
  {
    pregunta: "¿Lorem ipsum dolor sit amet?",
    respuesta: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero."
  },
]

export default function FAQ() {
  return (
    <div className='w-full h-screen flex items-center justify-center bg-gradient-to-bl from-[#DA291C] from-50% to-[#046A38] to-50% space-x-20'>
      <div className="font-bold text-white space-y-4 w-5/6 h-5/6 md:h-1/2 p-2 md:p-4 md:w-1/2 overflow-scroll no-scrollbar border-4 rounded-lg">
        <h1 className="text-4xl sm:text-5xl uppercase">F.A.Q</h1>
        <div className="space-y-10">
        {
          preguntas.map((pregunta, index) => (
            <div key={index}>
              <h3 className="text-2xl">
                {pregunta.pregunta}
              </h3>
              <p className="text-white text-wrap">
                {pregunta.respuesta}
              </p>
            </div>
          ))
        }
        </div>
      </div>
      {/* Barajitas */}
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
    </div>
  )
}