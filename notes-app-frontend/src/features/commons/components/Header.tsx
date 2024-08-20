import { Image, Link, User } from "@nextui-org/react";
import { useContext } from "react";
import { AuthContext } from "../../auth/context/AuthContext";

const Header = () => {
    const user = useContext(AuthContext)?.user;
    return (
        <header className="w-full bg-white shadow-md absolute top-0 left-0 right-0 z-50">
            <div className="flex flex-row items-center h-16">
                <div className="flex flex-row items-center ml-4 mr-auto">
                    <Image src="icons/notes.png" alt="Logo" width={40} height={40} />
                    <h1 className="text-2xl font-bold">Notes</h1>
                </div>
                <div className="flex space-x-4 mr-4">
                    {(user && user.username) &&
                        <User
                            name={user.username}
                            description={(
                                <Link href="" size="sm" isExternal>
                                    {user.email}
                                </Link>
                            )}
                        />
                    }
                </div>
            </div>
        </header>
    );
};

export default Header;
