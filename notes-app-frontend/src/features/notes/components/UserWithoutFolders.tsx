import { Button, Input, Modal, ModalBody, ModalContent, ModalFooter, ModalHeader, useDisclosure } from "@nextui-org/react"
import { useState } from "react";
import { useToast } from "../../commons/hooks/useToasts";

export const UserWithoutFolders = () => {
    const { isOpen, onClose, onOpen, onOpenChange } = useDisclosure();
    const [folderName, setFolderName] = useState<string>('');

    const toast = useToast();

    const validateFolderName = () => {
        if (folderName.length >= 0) {
            toast.add("Folder name can`t be empty", "warning");
            return false
        }
        return true

    }
    const createFolder = () => {
        if (!validateFolderName()) return;
        console.log(folderName);
        onClose();
    }
    return (
        <div className="flex flex-col items-center justify-center">
            <h1 className="text-2xl font-bold text-center">You don`t have any folder yet</h1>
            <Button
                color="primary"
                className="m-4"
                onClick={onOpen}
            >
                Create folder
            </Button>
            <Modal isOpen={isOpen} onOpenChange={onOpenChange}>
                <ModalContent>
                    {(onClose) => (
                        <>
                            <ModalHeader className="flex flex-col gap-1 text-2xl font-bold ml-4">
                                Create folder
                            </ModalHeader>
                            <ModalBody>
                                <Input
                                    name="folder name"
                                    label="Folder name"
                                    variant="underlined"
                                    placeholder="Folder name"
                                    fullWidth
                                    onChange={(e) => setFolderName(e.target.value)}
                                />
                            </ModalBody>
                            <ModalFooter>
                                <Button color="danger" variant="light" onPress={onClose}>
                                    Close
                                </Button>
                                <Button color="primary" onPress={createFolder}>
                                    Create
                                </Button>
                            </ModalFooter>
                        </>
                    )}
                </ModalContent>
            </Modal>
        </div >
    )
}