import { Image } from "@nextui-org/react";

const Header = () => {
    return (
        <header className="w-full bg-white shadow-md">
            <div className="flex flex-row items-center h-16">
                <div className="flex flex-row items-center ml-4 mr-auto">
                    <Image src="icons/notes.png" alt="Logo" width={40} height={40} />
                    <h1 className="text-2xl font-bold">Notes</h1>
                </div>
                <nav className="flex space-x-4 mr-4">
                    <a href="#" className="text-gray-500 hover:text-gray-700 px-3 py-2 rounded-md text-sm font-medium">Home</a>
                    <a href="#" className="text-gray-500 hover:text-gray-700 px-3 py-2 rounded-md text-sm font-medium">About</a>
                    <a href="#" className="text-gray-500 hover:text-gray-700 px-3 py-2 rounded-md text-sm font-medium">Contact</a>
                </nav>
            </div>
        </header>
    );
};

export default Header;
