import Navbar from "./components/Navbar"
import Landing from "./components/landing/Landing"
import FAQ from "./components/landing/FAQ"
import Testimonials from "./components/landing/Testimonials"
import Contact from "./components/landing/Contact"

const navbar_links = [
  {
    href: "#faq",
    text: "FAQs",
  },
  {
    href: "#testimonials",
    text: "Testimonios",
  },
  {
    href: "#contact",
    text: "Contacto",
  },
];

export default function Home() {
  return (
    <>
      <Navbar navigation_links={navbar_links}/>
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