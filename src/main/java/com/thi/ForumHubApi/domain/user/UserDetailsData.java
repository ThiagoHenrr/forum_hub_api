package com.thi.ForumHubApi.domain.user;

public record UserDetailsData(
        String name,
        String email
) {

    public UserDetailsData(User user){
        this(user.getName(), user.getEmail());
    }
}
