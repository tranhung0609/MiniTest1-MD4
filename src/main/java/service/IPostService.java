package service;

import model.Post;

public interface IPostService extends IGeneralService<Post> {
    Iterable<Post>findTop4Likes();
}
