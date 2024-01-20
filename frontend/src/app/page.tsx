import Navbar from "./components/Navbar"
import Landing from "./components/Landing"
import FAQ from "./components/FAQ"
import Testimonials from "./components/Testimonials"
import Contact from "./components/Contact"

export default function Home() {
  return (
    <>
      <Navbar/>
      <div id="home">
        <Landing/>
      </div>
      <div id="faq">
        <FAQ/>
      </div>
      <div id="testimonials">
        <Testimonials/>
      </div>
      <div id="contact">
        <Contact/>
      </div>
    </>
  )
}