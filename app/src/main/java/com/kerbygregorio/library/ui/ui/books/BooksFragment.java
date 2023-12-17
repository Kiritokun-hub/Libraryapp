package com.kerbygregorio.library.ui.ui.books;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kerbygregorio.library.Book;
import com.kerbygregorio.library.BookAdapter;
import com.kerbygregorio.library.DatabaseHelpers;
import com.kerbygregorio.library.DescriptionActivity;
import com.kerbygregorio.library.R;

public class BooksFragment extends Fragment {

    private RecyclerView recyclerView;
    private BookAdapter bookAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_books, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        bookAdapter = new BookAdapter(new BookAdapter.OnBookClickListener() {
            @Override
            public void onBookClick(Book book) {
                String _description = book.getDescription();
                openDescriptionActivity(_description);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(bookAdapter);

        insertSampleBooksIntoDatabase();
        loadBooksFromDatabase();

        return view;
    }

    private void openDescriptionActivity(String _description) {
        Intent intent = new Intent(requireContext(), DescriptionActivity.class);
        intent.putExtra("descriptions", _description);
        intent.putExtra("_descriptions", _description);
        startActivity(intent);
    }

    private void insertSampleBooksIntoDatabase() {
        DatabaseHelpers dbHelper = new DatabaseHelpers(requireContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String[] bookTitles = {
                "A Clockwork Orange",
                "Buddy Holly Is Alive and Well on Ganymede",
                "Captain Underpants and the Perilous Plot of Professor Poopypants",
                "Do Androids Dream of Electric Sheep?",
                "Extremely Loud & Incredibly Close",
                "Fahrenheit 451",
                "Go the Fuck to Sleep",
                "How to Lose Friends and Alienate People",
                "If on a Winter's Night a Traveler",
                "John Dies at the End",
                "Kitchen",
                "Lamb: The Gospel According to Biff, Christ's Childhood Pal",
                "Me Talk Pretty One Day",
                "No Country for Old Men",
                "One Hundred Years of Solitude",
                "Pride and Prejudice",
                "Quiet: The Power of Introverts in a World That Can't Stop Talking",
                "Rush Limbaugh is a Big Fat Idiot",
                "Skagboys",
                "The Lone Ranger and Tonto Fistfight in Heaven"
        };

        String[] authors = {
                "Anthony Burgess",
                "Bradley Denton",
                "Dav Pilkey",
                "Philip K. Dick",
                "Jonathan Safran Foer",
                "Ray Bradbury",
                "Adam Mansbach",
                "Toby Young",
                "Italo Calvino",
                "David Wong",
                "Banana Yoshimoto",
                "Christopher Moore",
                "David Sedaris",
                "Cormac McCarthy",
                "Gabriel García Márquez",
                "Jane Austen",
                "Susan Cain",
                "Al Franken",
                "Irvine Welsh",
                "Sherman Alexie"
        };

        String[] descriptions = {
                "Set in a dismal dystopian England, it is the first-person account of a juvenile delinquent who undergoes state-sponsored psychological rehabilitation for his aberrant behaviour.",
                "Buddy Holly is Alive and Well on Ganymede is a story of Oliver Vale who all his life been compared to Buddy Holly.",
                "The book is about a mad scientist named Professor Pippy P. Poopypants becoming a new science teacher at Jerome Horwitz Elementary.",
                "Rick Deckard, a bounty hunter, plans to kill enough errant androids (replicants) so he can replace his robotic sheep with a real one.",
                "Published in 2005, Jonathan Safran Foer's Extremely Loud & Incredibly Close tells the story of Oskar Schell, a nine-year-old boy who loses his father in the September 11 terrorist attacks and subsequently sets about solving a mystery.",
                "Fahrenheit 451 is a 1953 dystopian novel by American writer Ray Bradbury. It presents an American society where books have been personified and outlawed and 'firemen' burn any that are found.",
                "Summary. Go the Fuck to Sleep is written as a 'children's book for adults'. While its writing is in the style of classic children's bedtime stories, it includes the parent's language as commentary on the tricks used by Mansbach's daughter to avoid having to go to bed.",
                "How to Lose Friends & Alienate People is a 2008 British comedy film based upon Toby Young's 2001 memoir How to Lose Friends & Alienate People.",
                "If on a Winter's Night a Traveler is a marvel of ingenuity, an experimental text that looks longingly back to the great age of narration—'when time no longer seemed stopped and did not yet seem to have exploded.'",
                "The story focuses around the main character 'David Wong' (not his real name) and his penis-obsessed friend John who both unwittingly take the drug 'soy sauce' which, rather than killing them like it has other people, has the permanent effect of enabling them to see creatures from another hell-like world for which they ...",
                "Kitchen is an enchantingly original book that juxtaposes two tales about mothers, love, tragedy, and the power of the kitchen and home in the lives of a pair of free-spirited young women in contemporary Japan. Mikage, the heroine, is an orphan raised by her grandmother, who has passed away.",
                "Though funny and most definitely irreverent, Lamb is a story about the Son of God and his times here on Earth amongst people, told from the perspective of Biff, Joshua's (Jesus) childhood pal and Moore fills up the lost thirty some odd years from the gospels.",
                "Me Talk Pretty One Day is a humorous memoir by David Sedaris. It recounts the author's experiences as he tries to learn French in Paris and hilariously reflects on his struggles with language and cultural barriers.",
                "No Country for Old Men, a novel by Cormac McCarthy published in 2005, focuses on a drug deal gone wrong. While the novel encompasses big themes that define the modern era, its words bring to life the man who stumbles across the scene, the sociopathic hitman who pursues him, and the sheriff who tries to save him.",
                "One Hundred Years of Solitude is the history of the isolated town of Macondo and of the family who founds it, the Buendías. For years, the town has no contact with the outside world, except for gypsies who occasionally visit, peddling technologies like ice and telescopes.",
                "Pride and Prejudice is an 1813 novel of manners by English author Jane Austen. The novel follows the character development of Elizabeth Bennet, the protagonist of the book, who learns about the repercussions of hasty judgments and comes to appreciate the difference between superficial goodness and actual goodness.",
                "In Quiet, Susan Cain argues that we dramatically undervalue introverts and shows how much we lose in doing so. She charts the rise of the Extrovert Ideal throughout the twentieth century and explores how deeply it has come to permeate our culture. She also introduces us to successful introverts—from a witty, high- ...",
                "Rush Limbaugh Is a Big Fat Idiot and Other Observations ( ISBN 0-385-31474-4) is a 1996 American book by Al Franken. It is satirically critical of 1990s right-wing political figures such as Pat Buchanan, Bob Dole, Phil Gramm, Newt Gingrich, and particularly radio host Rush Limbaugh.",
                "Skagboys is a 2012 novel by Scottish writer Irvine Welsh. It is a prequel to his 1993 novel Trainspotting, and its 2002 sequel Porno. It follows the earlier lives of characters Renton and Sick Boy as they first descend into heroin addiction.",
                "\"The Lone Ranger and Tonto Fistfight in Heaven\" is one of Sherman Alexie's first collections of short stories. The collection deals with the lives and troubles of Indian in and around the Spokane Indian Reservation."
        };

        int[] imageResourceIds = {
                R.drawable.a,
                R.drawable.b,
                R.drawable.c,
                R.drawable.d,
                R.drawable.e,
                R.drawable.f,
                R.drawable.g,
                R.drawable.h,
                R.drawable.i,
                R.drawable.j,
                R.drawable.k,
                R.drawable.l,
                R.drawable.m,
                R.drawable.n,
                R.drawable.o,
                R.drawable.p,
                R.drawable.q,
                R.drawable.r,
                R.drawable.s,
                R.drawable.t,

        };

        for (int i = 0; i < bookTitles.length; i++) {
            ContentValues values = new ContentValues();
            values.put(DatabaseHelpers.COLUMN_NAME, bookTitles[i]);
            values.put(DatabaseHelpers.COLUMN_AUTHORS, authors[i]);
            values.put(DatabaseHelpers.COLUMN_DESCRIPTIONS, descriptions[i]);
            values.put(DatabaseHelpers.COLUMN_IMAGE_RESOURCE_ID, imageResourceIds[i]);

            db.insert(DatabaseHelpers.TABLE_NAME, null, values);
        }

        db.close();
    }

    private void loadBooksFromDatabase() {
        DatabaseHelpers dbHelper = new DatabaseHelpers(requireContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {DatabaseHelpers.COLUMN_NAME, DatabaseHelpers.COLUMN_AUTHORS, DatabaseHelpers.COLUMN_DESCRIPTIONS, DatabaseHelpers.COLUMN_IMAGE_RESOURCE_ID};
        Cursor cursor = db.query(DatabaseHelpers.TABLE_NAME, projection, null, null, null, null, null);

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelpers.COLUMN_NAME));
            String authors = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelpers.COLUMN_AUTHORS));
            String descriptions = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelpers.COLUMN_DESCRIPTIONS));
            int imageResourceId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelpers.COLUMN_IMAGE_RESOURCE_ID));

            Book book = new Book(name, authors, descriptions);
            book.setImageResourceId(imageResourceId);
            bookAdapter.addBook(book);
        }

        cursor.close();
        db.close();
    }
}
