db.createUser(
    {
        user: "notes_app",
        pwd: "notes_app",
        roles: [
            {
                role: "readWrite",
                db: "notes"
            }
        ]
    }
);