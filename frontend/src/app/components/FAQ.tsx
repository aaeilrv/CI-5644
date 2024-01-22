"use client";
import Image from "next/image";
import { useState } from "react"

const faq_data = [
  {
    Q: "¿Qué es Barajitas™?",
    A: "Barajitas™ es una página web dedicada a la gestión de álbumes virtuales y la colección e intercambio de cromos digitales con otros usuarios."
  },

  {
    Q: "¿Qué álbumes puedo coleccionar en el sitio?",
    A: "Por los momentos sólo está disponible el álbum Barajitas™ Heroes Nacionales, pero próximamente se añadirán nuevos albumes con cientos de cromos por coleccionar de tus personajes y eventos preferidos."
  },
  {
    Q: "¿Cómo puedo conseguir nuevos cromos?",
    A: "Podrás comprar en la tienda distintos paquetes que contendrán cromos aleatorios que te ayudaran a llenar las entradas de tu album virtual."
  },
  {
    Q: "¿Qué pasa si en los paquetes me salen cromos repetidos?",
    A: "Los cromos que tengas repetidos en tu inventario podrás intercambiarlos con otros usuarios de la plataforma que los necesiten para completar su colección a cambio de nuevos cromos para llenar entradas de tu álbum."
  },
  {
    Q: "¿Si cierro el navegador perderé mi progreso en el álbum?",
    A: "No. Al registrarte y crear un usuario en Barajitas™ todo tu progreso se guardará automáticamente y podras acceder a él nuevamente desde cualquier lugar y en cualquier momento iniciando sesión en tu cuenta."
  },
]

export default function FAQ() {
  const [selected, setSelected] = useState(null)

  const toggle = (index) => {
    if(selected == index){
      return setSelected(null)
    }

    setSelected(index)
  }


  return (
    <div className='w-full h-screen flex items-center justify-center bg-gradient-to-bl from-[#DA291C] from-50% to-[#046A38] to-50% space-x-20'>
      <div className="font-bold text-white space-y-4 w-5/6 h-5/6 md:h-1/2 p-2 md:p-4 md:w-1/2 overflow-scroll no-scrollbar border-4 rounded-lg">
        <h1 className="text-4xl sm:text-5xl uppercase">FAQ</h1>
        <div className="space-y-10">
        {
          faq_data.map((faq_data, index) => (
            <div key={index}>
              <button className="text-2xl display:flex text-left align-item:left" onClick = {() => toggle(index)}>
                  {faq_data.Q}
                  <span> { selected == index ? "-" : "+"} </span>
              </button>
              <div className ={
              selected == index ? "text-white text-wrap heigth: auto max-heigth: 999px transition-all 1.5s cubic-bezier:(1,0,1,0) " : "text-white text-wrap overflow: hidden transition-all 1.5s cubic-bezier:(0,1,0,1)"}>
                {faq_data.A}
              </div>
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