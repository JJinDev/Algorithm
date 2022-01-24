import java.util.*;

class L3_베스트앨범 {
    HashMap<String, Integer> genreCount;
    HashMap<String, List<Song>> songs;
    
    public class Genre implements Comparable<Genre> {
        String name;
        int count;
        
        public Genre(String name, int count) {
            this.name = name;
            this.count = count;
        }
        
        public int compareTo(Genre o) {
            return o.count - this.count;
        }
    }
    
    public class Song implements Comparable<Song> {
        int playCount;
        int number;
        
        public Song(int playCount, int number) {
            this.playCount = playCount;
            this.number = number;
        }
        
        public int compareTo(Song o) {
            if (this.playCount == o.playCount) {
                return this.number - o.number;
            } else {
                return o.playCount - this.playCount;
            }
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        init();
        setSongs(genres, plays);
        return getAnswer();
    }
    
    public int[] getAnswer() {
        List<Integer> temp = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        int size = genreCount.size();
        int index = 0;
        
        Iterator<String> it = genreCount.keySet().iterator();
        
        for (int i = 0; i < size; i++) {
            String genreName = it.next();
            genres.add(new Genre(genreName, genreCount.get(genreName)));
        }
        
        Collections.sort(genres);
        
        for (int i = 0; i < genres.size(); i++) {
            Genre genre = genres.get(i);
            
            List<Song> list = songs.get(genre.name);
            Collections.sort(list);
            
            int count = 0;
            for (int j = 0; j < list.size(); j++) {
                if (count == 2) {
                    break;
                }
                temp.add(list.get(j).number);
                count++;
            }
        }
        
        int[] answer = new int[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            answer[i] = temp.get(i);
        }
        return answer;
    }
    
    public void setSongs(String[] genres, int[] plays) {
        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            int play = plays[i];
            
            if (songs.containsKey(genre)) {
                songs.get(genre).add(new Song(play, i));
                genreCount.put(genre, genreCount.get(genre) + play);
            } else {
                List<Song> list = new ArrayList<>();
                list.add(new Song(play, i));
                songs.put(genre, list);
                genreCount.put(genre, play);
            }
        }    
    }
    
    public void init() {
        genreCount = new HashMap<>();
        songs = new HashMap<>();
    }
}