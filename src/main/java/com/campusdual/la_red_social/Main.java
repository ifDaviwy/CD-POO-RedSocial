package com.campusdual.la_red_social;

import java.util.*;
import com.campusdual.la_red_social.Post.*;
import com.campusdual.util.*;

public class Main {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();

        // Añado dos usuarios
        User usuario1 = new User("David");
        User usuario2 = new User("Pedro");
        users.add(usuario1);
        users.add(usuario2);

        usuario1.follow(usuario2);

        // Creo y añado post para David
        Post postTextoDavid = new TextPost(usuario1, "Hola, este es mi primer post en esta red social");
        usuario1.addPost(postTextoDavid);

        // Creo y añado posts para Pedro
        Post postTextoUser2 = new TextPost(usuario2, "Bienvenidos a mi perfil");
        Post postImagenUser2 = new ImagePost(usuario2, "800x600", "Mi primera foto");
        usuario2.addPost(postTextoUser2);
        usuario2.addPost(postImagenUser2);


        while (true) {
            System.out.println("1. Añadir usuario");
            System.out.println("2. Publicar post");
            System.out.println("3. Dejar de seguir usuario");
            System.out.println("4. Seguir usuario");
            System.out.println("5. Borra usuario");
            System.out.println("6. Listar posts de un usuario");
            System.out.println("7. Listar los comentarios que tiene un usuario en todos sus posts");
            System.out.println("8. Ver numero de comentarios de un usuario");
            System.out.println("9. Ver muro de un usuario");
            System.out.println("10. Comentar un post");
            System.out.println("11. Borrar un post o comentario");
            System.out.println("0. Salir");

            int option = Input.integer("Elige una opcion: ");

            switch (option) {
                case 1:
                    String name = Input.string("Ingresa el nombre del nuevo usuario: ");
                    if (findUser(users, name) == null) {
                        users.add(new User(name));
                        System.out.println("Usuario agregado.");
                    } else {
                        System.out.println("El nombre de usuario ya existe.");
                    }
                    break;

                case 2:
                    String userName = Input.string("Ingresa el nombre del usuario que publicará el post: ");
                    User user = findUser(users, userName);
                    if (user != null) {
                        String type = Input.string("Tipo de post (texto/imagen/video): ");
                        Post post = null;
                        if ("texto".equalsIgnoreCase(type)) {
                            String content = Input.string("Contenido del texto: ");
                            post = new TextPost(user, content);
                        } else if ("imagen".equalsIgnoreCase(type)) {
                            String titleImage = Input.string("Título de la imagen: ");
                            String dimensions = Input.string("Dimensiones de la imagen: ");
                            post = new ImagePost(user, dimensions, titleImage);
                        } else if ("video".equalsIgnoreCase(type)) {
                            String titleVideo = Input.string("Título del video: ");
                            String quality = Input.string("Calidad del video: ");
                            int duration = Input.integer("Duración del video en segundos: ");
                            post = new VideoPost(user, quality, duration, titleVideo);
                        } else {
                            System.out.println("Tipo de post no reconocido.");
                            break;
                        }
                        post.setOwner(user);
                        user.addPost(post);
                        System.out.println("Post añadido.");
                    } else {
                        System.out.println("Usuario no encontrado.");
                    }
                    break;

                case 3:
                    String yourName = Input.string("Ingresa tu nombre de usuario: ");
                    String unfollowName = Input.string("Ingresa el nombre de usuario que deseas dejar de seguir: ");
                    User you = findUser(users, yourName);
                    User unfollowUser = findUser(users, unfollowName);
                    if (you != null && unfollowUser != null) {
                        you.unfollow(unfollowUser);
                        System.out.println("Usuario dejado de seguir.");
                    } else {
                        System.out.println("Usuario no encontrado.");
                    }
                    break;

                case 4:
                    String myName = Input.string("Ingresa tu nombre de usuario: ");
                    System.out.println("Sugerencias de usuarios para seguir:");
                    User currentUser = findUser(users, myName);
                    if (currentUser != null) {
                        List<User> suggestedUsers = currentUser.suggestFriends(users);
                        for (User suggestedUser : suggestedUsers) {
                            System.out.println(suggestedUser.getName());
                        }
                    }

                    System.out.println("Usuarios disponibles para seguir:");
                    for (User u : users) {
                        System.out.println(u.getName());
                    }

                    String followName = Input.string("Ingresa el nombre del usuario que quieres seguir: ");
                    User me = findUser(users, myName);
                    User followUser = findUser(users, followName);

                    if (me != null && followUser != null) {
                        if (me.equals(followUser)) {
                            System.out.println("No puedes seguirte a ti mismo.");
                            break;
                        }

                        if (!me.getFollowing().contains(followUser)) {
                            me.follow(followUser);
                            System.out.println("Usuario seguido.");
                        } else {
                            System.out.println("Ya estás siguiendo a este usuario.");
                        }
                    } else {
                        System.out.println("Usuario(s) no encontrado(s).");
                    }
                    break;

                case 5:
                    String deleteName = Input.string("Ingresa el nombre de usuario a borrar: ");
                    User deleteUser = findUser(users, deleteName);
                    if (deleteUser != null) {
                        users.remove(deleteUser);
                        System.out.println("Usuario borrado.");
                    } else {
                        System.out.println("Usuario no encontrado.");
                    }
                    break;

                case 6:
                    String listName = Input.string("Ingresa el nombre de usuario cuyos posts deseas listar: ");
                    User listUser = findUser(users, listName);
                    if (listUser != null) {
                        List<Post> posts = listUser.getPosts();
                        for (Post p : posts) {
                            System.out.println("Fecha: " + p.getDate());
                            System.out.println("ID: " + p.getId() + " " + p.getContentDetails());
                        }
                    } else {
                        System.out.println("Usuario no encontrado.");
                    }
                    break;

                case 7:
                    String listCommentsName = Input.string("Ingresa el nombre de usuario cuyos comentarios deseas listar: ");
                    User listCommentsUser = findUser(users, listCommentsName);
                    if (listCommentsUser != null) {
                        List<Post> posts = listCommentsUser.getPosts();
                        for (Post p : posts) {
                            List<Comment> comments = p.getComments();
                            for (Comment c : comments) {
                                System.out.println("De: " + c.getOwner().getName());
                                System.out.println(c.getDate());
                                System.out.println(c.getText());
                                System.out.println("---");
                            }
                        }
                    } else {
                        System.out.println("Usuario no encontrado.");
                    }
                    break;

                case 8:
                    String listCommentsPostName = Input.string("Ingresa el nombre de usuario que desees saber cuantos comentarios tiene: ");
                    User listCommentsPostUser = findUser(users, listCommentsPostName);
                    if (listCommentsPostUser != null) {
                        List<Post> posts = listCommentsPostUser.getPosts();
                        for (Post p : posts) {
                            System.out.println("Post ID: " + p.getId() + " - " + p.getContentDetails() + " - tiene " + p.numberOfComments() + " comentarios.");
                            //System.out.println(p.numberOfComments());
                        }
                    } else {
                        System.out.println("Usuario no encontrado.");
                    }
                    break;

                case 9:
                    String username = Input.string("Ingresa el nombre de usuario para ver su muro: ");
                    User fUser = findUser(users, username);
                    if (fUser != null) {
                        fUser.showWall();
                    } else {
                        System.out.println("Usuario no encontrado.");
                    }
                    break;

                case 10:
                    String commenterName = Input.string("Ingresa tu nombre de usuario: ");
                    User commenter = findUser(users, commenterName);
                    if (commenter != null) {
                        String postOwnerName = Input.string("Ingresa el nombre de usuario dueño del post: ");
                        User postOwner = findUser(users, postOwnerName);
                        if (postOwner != null) {
                            List<Post> posts = postOwner.getPosts();
                            for (Post p : posts) {
                                System.out.println("Fecha: " + p.getDate());
                                System.out.println("ID: " + p.getId() + " " + p.getContentDetails());
                                System.out.println("---");
                            }
                            int postId = Input.integer("Ingresa el ID del post: ");
                            Post postToComment = postOwner.findPostById(postId);
                            if (postToComment != null) {
                                String commentContent = Input.string("Escibe el contenido del comentario: ");
                                commenter.commentOnPost(postToComment, commentContent);
                                System.out.println("Comentario añadido.");
                            } else {
                                System.out.println("ID de post no encontrado.");
                            }
                        } else {
                            System.out.println("Dueño de post no encontrado.");
                        }
                    } else {
                        System.out.println("usuario no encontrado.");
                    }
                    break;

                case 11:
                    System.out.println("¿Qué deseas borrar?");
                    System.out.println("1. Post");
                    System.out.println("2. Comentario");
                    int deleteOption = Input.integer("Elige una opción: ");
                    switch (deleteOption) {
                        case 1:
                            String postOwnerName = Input.string("Ingresa el nombre de usuario dueño del post: ");
                            User postOwner = findUser(users, postOwnerName);
                            if (postOwner != null) {
                                int postId = Input.integer("Ingresa el ID del post: ");
                                if (postOwner.deletePostById(postId)) {
                                    System.out.println("Post borrado.");
                                } else {
                                    System.out.println("Post no encontrado.");
                                }
                            } else {
                                System.out.println("Usuario no encontrado.");
                            }
                            break;

                        case 2:
                            String commentOwnerName = Input.string("Ingresa el nombre de usuario que ha sido comentado: ");
                            User commentOwner = findUser(users, commentOwnerName);
                            if (commentOwner != null) {
                                int commentId = Input.integer("Ingresa el ID del comentario:");
                                if (commentOwner.deleteCommentById(commentId)) {
                                    System.out.println("Comentario borrado.");
                                } else {
                                    System.out.println("Comentario no encontrado.");
                                }
                            } else {
                                System.out.println("Usuario no encontrado.");
                            }
                            break;
                        default:
                            System.out.println("Opción no válida.");
                            break;
                    }
                    break;

                case 0:
                    System.out.println("Has salido del programa.");
                    return;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }

    // Buscar un usuario en la lista por su nombre, asi evito buscar por el indice de la lista
    public static User findUser(List<User> users, String name) {
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }
}