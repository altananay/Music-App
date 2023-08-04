package com.atmosware.musicapp.common.constants;

public class Messages {

    private Messages() {}

    public static class Admin
    {
        private Admin() {}

        public static final String NOT_EXISTS = "Admin bulunamadı";
    }

    public static class Album
    {
        private Album() {}

        public static final String NOT_EXISTS = "Album bulunamadı";
    }

    public static class ArtistAlbum
    {
        private ArtistAlbum() {}

        public static final String NOT_EXISTS = "Sanatçının albumu bulunamadı";
    }

    public static class Artist
    {
        private Artist() {}

        public static final String NOT_EXISTS = "Sanatçı bulunamadı";
    }

    public static class ArtistSong
    {
        private ArtistSong() {}

        public static final String NOT_EXISTS = "Sanatçının şarkıları bulunamadı";
        public static final String NOT_EXISTS_BY_SONG_ID = "Muzigin sanatçı bilgisi yok";
    }

    public static class PopularSong
    {
        private PopularSong() {}

        public static final String NOT_EXISTS = "Popular muzik bulunamadı";
    }

    public static class Song
    {
        private Song() {}

        public static final String NOT_EXISTS = "Muzik bulunamadı";
    }

    public static class SongStyle
    {
        private SongStyle() {}

        public static final String NOT_EXISTS = "Muzik tarzı bulunamadı";
    }

    public static class Style
    {
        private Style() {}

        public static final String NOT_EXISTS = "Müzik tarzı bulunamadı";
    }

    public static class User
    {
        private User() {}

        public static final String NOT_EXISTS = "Kullanıcı bulunamadı";
    }

    public static class UserFavoriteSong
    {
        private UserFavoriteSong() {}

        public static final String NOT_EXISTS = "Kullanıcının favori muzikleri bulunamadı";
    }

    public static class UserFollower
    {
        private UserFollower() {}

        public static final String NOT_EXISTS = "Kullanıcının takipçileri bulunamadı";
        public static final String USERS_NOT_FOLLOWING_EACH_OTHER = "Kullanıcılar birbirlerini takip etmiyor.";
    }

    public static class AlbumSong
    {
        private AlbumSong() {}

        public static final String NOT_EXISTS = "Albume ait şarkılar bulunamadı";
    }

    public static class JwtPayload
    {
        private JwtPayload() {}

        public static final String ROLES = "roles";
        public static final String EMAIL = "email";
    }

    public static class Authentication
    {
        private Authentication() {}

        public static final String REGISTER_SUCCESSFUL = "Başarıyla kayıt oldunuz.";
        public static final String AUTH_SUCCESSFUL = "Giriş başarılı";
    }

    public static class JwtRequest
    {
        private JwtRequest() {}

        public static final String REQUEST_HEADER = "Authorization";
        public static final String TOKEN_PREFIX = "Bearer ";
        public static final String ROLE_PREFIX = "ROLE_";
    }
}