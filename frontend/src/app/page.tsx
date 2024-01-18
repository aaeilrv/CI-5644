export default function Home() {
  return (
    <>
      { /* Navbar */}
      { /* Background */}
      <div className='grid grid-cols-12 gap-4 h-max bg-[#75aadb]'>
        {
          Array(12).fill(0).map((_, i) => (
            <div key={i} className='relative'>
              <div className="w-full h-dvh bg-[#83b2df]"></div>
            </div>
          ))
        }
      </div>
    </>
  )
}