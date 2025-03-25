import { MergeForm } from '../components/MergeForm';
import Image from 'next/image';

// export default function Home() {
//     return (
//         <div className="flex w-full h-screen overflow-y-auto">
//             <div className="flex flex-col  h-full w-full">
//                 {/* Logo */}
//                 <div className="flex w-full overflow-hidden flex items-center justify-center bg-gradient-custom">

//                 <div className="max-h-[100px] overflow-hidden flex items-center justify-center mb-4">
//                     <Image
//                         src="/logo-powerpdf.svg"
//                         alt="PowerPDF Logo"
//                         width={200}
//                         height={48}
//                         priority
//                         className="max-h-full object-contain"
//                     />
//                 </div>
//                 </div>
                
//                 {/* Formulário de Merge */}
//                 <div className="flex flex-col w-full max-w-3xl bg-white rounded-lg shadow-lg p-6 pt-4 space-y-4">
//                     <MergeForm />
//                 </div>
//             </div>
//         </div>

//     );
// } 
export default function Home() {
    const [showLogin, setShowLogin] = useState(false);
    const [showRegister, setShowRegister] = useState(false);

    return (
        <div>
            <div className="grid grid-nogutter surface-0 text-800">
                <div className="col-12 md:col-6 p-6 text-center md:text-left flex align-items-center ">
                    <section>
                        <span className="block text-6xl font-bold mb-1">Create the screens</span>
                        <div className="text-6xl text-primary font-bold mb-3">your visitors deserve to see</div>
                        <p className="mt-0 mb-4 text-700 line-height-3">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>

                      
                    </section>
                </div>
                <div className="col-12 md:col-6 overflow-hidden">
                    <img src="/demo/images/blocks/hero/hero-1.png" alt="hero-1" className="md:ml-auto block md:h-full" style={{ clipPath: 'polygon(8% 0, 100% 0%, 100% 100%, 0 100%)' }} />
                </div>
            </div>
           
        
        </div>

  );
}