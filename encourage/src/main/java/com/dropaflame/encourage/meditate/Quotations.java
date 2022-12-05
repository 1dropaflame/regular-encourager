package com.dropaflame.encourage.meditate;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
@Scope("singleton")
public class Quotations {
    int indexAcceptedInChrist = 0;
    ArrayList<String> acceptedInChrist = new ArrayList<>(Arrays.asList(
            "John 1:12 I am God's child.                                                               ",
            "John 15:15 As a disciple, I am a friend of Jesus Christ.                                  ",
            "Romans 5:1 I have been justified (declared righteous).                                    ",
            "1 Corinthians 6:17 I am united with the Lord, and I am one with Him in spirit.            ",
            "1 Corinthians 6:19-20 I have been bought with a price and I belong to God.                ",
            "1 Corinthians 12:27 I am a member of Christ's body.                                       ",
            "Ephesians 1:3-8 I have been chosen by God and adopted as His child.                       ",
            "Colossians 1:13-14 I have been redeemed and forgiven of all my sins.                      ",
            "Colossians 2:9-10 I am complete in Christ.                                                ",
            "Hebrews 4:14-16 I have direct access to the throne of grace through Jesus Christ.         "));
    int indexSecureInChrist = 0;
    ArrayList<String> secureInChrist = new ArrayList<>(Arrays.asList(
            "Romans 8:1-2 I am free from condemnation.                                                 ",
            "Romans 8:28 I am assured that God works for my good in all circumstances.                 ",
            "Romans 8:31-39 I am free from any condemnation brought against me and I cannot be         ",
            "separated from the love of God.                                                           ",
            "2 Corinthians 1:21-22 I have been established, anointed and sealed by God.                ",
            "Colossians 3:1-4 I am hidden with Christ in God.                                          ",
            "Philippians 1:6 I am confident that God will complete the good work He started in me.     ",
            "Philippians 3:20 I am a citizen of heaven.                                                ",
            "2 Timothy 1:7 I have not been given a spirit of fear but of power, love and a sound mind. ",
            "1 John 5:18 I am born of God and the evil one cannot touch me.                            "));
    int indexSignificantInChrist = 0;
    ArrayList<String> significantInChrist = new ArrayList<>(Arrays.asList(
            "John 15:5 I am a branch of Jesus Christ, the true vine, and a channel of His life.        ",
            "John 15:16 I have been chosen and appointed to bear fruit.                                ",
            "1 Corinthians 3:16 I am God's temple.                                                     ",
            "2 Corinthians 5:17-21 I am a minister of reconciliation for God.                          ",
            "Ephesians 2:6 I am seated with Jesus Christ in the heavenly realm.                        ",
            "Ephesians 2:10 I am God's workmanship.                                                    ",
            "Ephesians 3:12 I may approach God with freedom and confidence.                            ",
            "Philippians 4:13 I can do all things through Christ, who strengthens me.                  "));
    int indexNoAnxiety = 0;
    ArrayList<String> noAnxiety =
            new ArrayList<>(Arrays.asList(
                    "Don’t be anxious about anything: Philippians 4:6-7 \"Do not be anxious about anything, but in every situation, by prayer and petition, with thanksgiving, present your requests to God. And the peace of God, which transcends all understanding, will guard your hearts and your minds in Christ Jesus\" (NIV).",
                    "Give God your worry: 1 Peter 5:6-7 \" Humble yourselves, therefore, under God’s mighty hand, that he may lift you up in due time. Cast all your anxiety on him because he cares for you \" (NIV).",
                    "He will sustain you in the storm: Psalm 55:22 \" Cast your cares on the Lord and he will sustain you; he will never let the righteous be shaken\" (NIV).",
                    "We can trust God completely: Proverbs 3:5-6\"Trust in the Lord with all your heart and lean not on your own understanding; in all your ways submit to him, and he will make your paths straight \" (NIV).",
                    "God is bigger than our fearful hearts: Isaiah 35:4  \"Say to those with fearful hearts, 'Be strong, do not fear; your God will come, he will come with vengeance; with divine retribution he will come to save you' \" (NIV)."));

    public Quotations() {

    }

    public String getQuotation(String mood) {
        String quote;
        switch (mood) {
            case "rejected",
                    "hated",
                    "despised",
                    "depressed":
                System.out.println(indexAcceptedInChrist + " indexAcceptedInChrist");
                quote = acceptedInChrist.get(indexAcceptedInChrist++ % acceptedInChrist.size());
                return quote;
            case "worthless",
                    "failure",
                    "useless":
                System.out.println(indexSignificantInChrist + " indexSignificantInChrist");
                quote = significantInChrist.get(indexSignificantInChrist++ % significantInChrist.size());
                return quote;
            case "afraid",
                    "sinned",
                    "suicide":
                System.out.println(indexSecureInChrist + " indexSecureInChrist");
                quote = secureInChrist.get(indexSecureInChrist++ % secureInChrist.size());
                return quote;
            case "worried",
                    "anxiety":
                System.out.println(indexNoAnxiety + " indexNoAnxiety");
                quote = noAnxiety.get(indexNoAnxiety++ % noAnxiety.size());
                return quote;
            default:
                return "We walk by Faith, not by Sight";
        }
    }
}
