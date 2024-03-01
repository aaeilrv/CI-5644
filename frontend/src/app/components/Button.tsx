import Link from "next/link";

export default function Button({ link, text } : {
  link: string,
  text: string
}) {
  return (
    <button className="rounded bg-indigo-500 px-2 py-2 text-sm font-semibold text-white shadow-sm hover:bg-indigo-400 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-500">
      <Link href={link}>{text}</Link>
    </button>
  )
}