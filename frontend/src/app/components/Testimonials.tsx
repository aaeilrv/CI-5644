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

  return (
    <div className='min-h-screen flex items-center justify-center space-x-10 overflow-hidden py-10 bg-gradient-to-tl from-[#21304D] from-50% to-[#182640] to-50%'>
      <div className="grid grid-cols-1 justify-items-center">
        <div className="text-white">
          <h1 className="font-bold text-4xl sm:text-5xl uppercase">Testimonios</h1>
        </div>

        {/* Testimonials */}
        <div className={`grid grid-cols-1 md:grid-cols-4 gap-10 mt-10 place-content-end p-4 md:p-0`}>
          {
            testimonials.map((testimonial, index) => (
              <div className="relative block md:w-48 md:h-64 p-2 border-4 border-[#ce1939] rounded-lg text-white overflow-scroll no-scrollbar" key={index}>
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
