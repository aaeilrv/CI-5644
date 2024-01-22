"use client";

const testimonials = [
  {
    comment: "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
    author: "Pepito",
  },
  {
    comment: "Bla bla bla",
    author: "Pepito",
  },
  {
    comment: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum is simply dummy text of the printing and typesetting industry",
    author: "Pepito",
  },
  {
    comment: "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
    author: "Pepito",
  },
]


export default function Testimonials() {
  const lg_num_testimonials = testimonials.length >= 8 ? 8: testimonials.length
  const md_num_testimonials = testimonials.length >= 4 ? 4: testimonials.length
  const sm_num_testimonials = testimonials.length >= 4 ? 4: testimonials.length

  return (
    <div className='bg-[#202437] min-h-screen flex items-center justify-center space-x-10 overflow-hidden py-10'>
      <div className="grid grid-cols-1 justify-items-center">
        <div className="text-white">
          <h1 className="font-bold text-4xl sm:text-5xl uppercase">Testimonios</h1>
        </div>

        {/* Comentarios */}
        <div className={`grid grid-cols-1 sm:grid-cols-${sm_num_testimonials} md:grid-cols-${md_num_testimonials} lg:grid-cols-${lg_num_testimonials} gap-10 mt-10 place-content-end`}>
          {
            testimonials.map((testimonial, index) => (
              <div className="relative block w-48 h-64 p-2 border-8 border-[#ce1939] rounded-lg text-white overflow-scroll no-scrollbar" key={index}>
                <h5 className="mb-2 font-bold italic">&quot;{testimonial.comment}&quot;</h5>
                <p className="font-normal text-left">-{testimonial.author}</p>
              </div>
            ))
          }
        </div>
      </div>
    </div>
  )
}
