import type { Metadata } from 'next'
import { Inter } from 'next/font/google'

const inter = Inter({ subsets: ['latin'] })

export const metadata: Metadata = {
  title: 'Barajitas',
  description: 'Intercambia barajitas',
}

export default function RootLayout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    <html lang="en">
      <body className={inter.className}>
        <div className="h-full min-h-screen flex items-center justify-center bg-[#DFEEEE]">
          {children}
        </div>
      </body>
    </html>
  )
}
