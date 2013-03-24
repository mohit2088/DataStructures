import java.util.Set;
import java.util.TreeSet;

public class Trie {
 TrieNode root;
 int count;

  Trie (TrieNode root, int count) {
  this.root = root;
  this.count = count;
 }

  public static void main(String args[]) {
  Trie trie = new Trie(new TrieNode(' '), 1);  
  trie.insert("Mohit");
  trie.insert("Hi");
  trie.insert("Hill");
  trie.insert("Heel");
  trie.insert("Hell");
  trie.insert("9666670664");
  System.out.println(trie.search("Hell"));
  trie.delete("Hell");
  System.out.println(trie.search("Hell"));
  System.out.println(trie.search("Hell"));
 }

  public boolean insert(String word) {  
  // Invalid params
  if (word == null || this.root == null)
   return false;

   // Default to root of the Trie
  TrieNode node = this.root;
  boolean found = false;

   // Loop through all the letters in the word to insert
  for (int i = 0; i < word.length() ; i++) {

    // Insert if not already in the right place
   for (TrieNode t : node.children) {

     if (t.data == word.charAt(i)) {
     node = t;
     found = true;
     break;
    }
   }

    if (!found) {
    TrieNode newNode = new TrieNode(word.charAt(i));
    node.children.add(newNode);
    node = newNode;
   }
   else found = false;  
  }

   // Mark the leaf node, to depict the word as a key.
  node.leaf = this.count++;
  return true;
 }

  public boolean search(String word) {
  // Invalid params
  if (word == null || this.root == null)
   return false;

   // Default to root of the Trie
  TrieNode node = this.root;
  boolean found = false;

   // Loop through all the letters in the word to insert
  for (int i = 0; i < word.length() ; i++) {
   // Insert if not already in the right place
   for (TrieNode t : node.children) {
    if (t.data == word.charAt(i)) {
     node = t;
     found = true;
     break;
    }
   }

    if (!found) return false;
   else found = false;   
  }

   // Validation to check for key
  // Check the leaf node for a count
  if (node.leaf != 0)
   return true;
  return false;
 }

  public boolean delete(String word) {
  if (word == null || this.root == null)
   return false;

   return deleteHelper(this.root, 0, word);
 }

  private boolean deleteHelper(TrieNode node, int level, String word) {
  if (node == null)
   return false;

   // Base case:
  if (level == word.length()) {
   if (!node.children.isEmpty())
    node.leaf = 0;
   else
    return true;
  }
  else {
   TrieNode temp = null;
   for (TrieNode t : node.children) {
    if (t.data == word.charAt(level)) {
     temp = t;
     break;
    }
   }

    if (deleteHelper(temp, level + 1, word)) {
    node.children.remove(temp);
    return (node.leaf == 0 && node.children.isEmpty());
   }
  }
  return false;   
 }
}

class TrieNode implements Comparable{
 char data;
 String associatedInfo;
 Set children;

  TrieNode (char data){
  this.data = data;
  children = new TreeSet();
 }

  @Override
 public int compareTo(TrieNode o) {
  return Character.valueOf(this.data).compareTo(Character.valueOf(o.data));
 }  

  public String toString() {
  return String.valueOf(this.data);
 }
}
