import Image from "next/image";
import UploadForm from "./components/MergeFilesForm";
import MergedFiles from "./components/MergedFiles";

export default function Home() {
  return (
    <div className="flex flex-col min-h-screen justify-between">
      <main className="flex flex-col gap-3 row-start-2 items-center sm:items-start">
        <div className="flex w-full overflow-hidden items-center justify-center bg-gradient-custom h-[100px]">
          <Image
            src="/logo-powerpdf.svg"
            alt="PowerPDF Logo"
            width={180}
            height={38}
            priority
            className="max-h-full object-contain "
          />
        </div>

        <UploadForm />
        <MergedFiles />

      </main>
      <footer className="row-start-3 flex gap-[24px] flex-wrap items-center justify-center">
        <div className="flex w-full overflow-hidden items-center justify-center">
          <Image
            src="/logo-powercrm.svg"
            alt="PowerCRM Logo"
            width={180}
            height={38}
            priority
            className="max-h-full object-contain "
          />
        </div>

      </footer>
    </div>
  );
}
