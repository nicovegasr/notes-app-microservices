import { Button, Input } from "@nextui-org/react";
import { ChangeEvent, FormEvent, useState } from "react";
import { User } from "../../../models/User";

interface UserFormProps {
    mode: string;
    onSend: (user: User) => void;
}

export const UserForm = ({ mode, onSend }: UserFormProps) => {
    const [user, setUser] = useState<User>({ username: '', email: '', password: '' } as User);

    const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
        const { name, value } = event.target;
        setUser({ ...user, [name]: value });
    };

    const handleSubmit = (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        onSend(user);
    };

    return (
        <form className="h-full w-full" onSubmit={handleSubmit}>
            <div className="flex w-full flex-wrap md:flex-nowrap mb-4">
                <Input
                    name="username"
                    label="Username"
                    variant="underlined"
                    placeholder="Username"
                    fullWidth
                    autoComplete={"username"}
                    onChange={handleInputChange}
                />
            </div>
            {mode === 'register' &&
                <div className="flex w-full flex-wrap md:flex-nowrap mb-4">
                    <Input
                        name="email"
                        label="Email"
                        variant="underlined"
                        placeholder="Email"
                        fullWidth
                        autoComplete="email"
                        onChange={handleInputChange}
                    />
                </div>
            }
            <div className="flex w-full flex-wrap md:flex-nowrap mb-4">
                <Input
                    name="password"
                    label="Password"
                    variant="underlined"
                    placeholder="Password"
                    fullWidth
                    type="password"
                    autoComplete={mode === 'login' ? "current-password" : "new-password"}
                    onChange={handleInputChange}
                />
            </div>
            <Button
                type="submit"
                className="w-full py-2 bg-blue-500 text-white font-bold rounded-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-opacity-50 transition duration-300"
            >
                {mode === 'login' ? 'Login' : 'Register'}
            </Button>
        </form>
    )
}